package com.cskt.itripauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cskt.itripauth.common.constants.SystemConstants;
import com.cskt.itripauth.service.MailService;
import com.cskt.itripauth.service.SmsService;
import com.cskt.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cskt.constants.ErrorCodeEnum;
import com.cskt.entity.User;
import com.cskt.vo.UserRegisterCondition;
import com.cskt.itripauth.common.exception.ServiceException;
import com.cskt.itripauth.service.UserService;
import com.cskt.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.annotation.Nonnull;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.cskt.constants.ErrorCodeEnum.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    String activeCodeKeyPre = "active:";

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MailService mailService;

    @Value(value = "${email.send.enable}")
    private boolean enableSendEmail; //邮箱

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SmsService smsService;

    @Override
    public boolean userRegister(UserRegisterCondition condition) {

        try{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //用户账号是否等于
        queryWrapper.eq("user_code",condition.getUserCode());
        User oneExist = this.getOne(queryWrapper);
        if (oneExist!=null) {
            //用户已经存在直接抛出异常，
            throw new ServiceException(AUTH_USER_ALREADY_EXISTS);
        }
        //先完成实体类转换
        condition.setUserPassword(MD5.getMd5(condition.getUserPassword(), 32));
        User user = new User();
        BeanUtils.copyProperties(condition,user);
        //初始化其他数据，指定用户类型（默认为自注册用户）
        user.setUserType(SystemConstants.REGISTRATION);

        //1.将用户信息存入数据库
        this.save(user);
        //2.生成激活码，通过当前系统时间缀进行32为MD5加密
        String activationCode = MD5.getMd5(String.valueOf(System.currentTimeMillis()), 32);
        log.info("激活码:{}",activationCode);
        //3.发送邮件
        //可以通过定义一个发送邮件的开关，根据不同的环境来确定是否需要发生，
        // 譬如开发环境和测试环境就可以不用发送，只需要在生产环境
        if (enableSendEmail){
            mailService.sendActivationMail(user.getUserCode(),activationCode);
        }
        //4.激活码存入Redis中，过期时间30分钟
        stringRedisTemplate.opsForValue().set(activeCodeKeyPre+user.getUserCode(),activationCode,30, TimeUnit.MINUTES);
    } catch (BeansException e) {
        throw new ServiceException(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR);
    }
    return true;

    }

    /**
     * 根据参数
     * 二即用户输入的激活码到Redis数据库去查询
     * 如果跟Redis数据库中保存的激活码一样，
     * 则表示验证成功
     * @param userCode 邮箱地址
     * @param code 激活码
     * @return
     */
    @Override
    public boolean active(@Nonnull String userCode,@Nonnull String code) {
        if (stringRedisTemplate.hasKey(activeCodeKeyPre+userCode)){
            //先验证激活码是否有效
            String activeCode = stringRedisTemplate.opsForValue().get(activeCodeKeyPre + userCode);
            if (StringUtils.hasText(activeCode)) {
                if (code.equals(activeCode)) {
                    QueryWrapper<User> userWrapper = new QueryWrapper<>();
                    userWrapper.eq("user_code",userCode);
                    User user = this.getOne(userWrapper);
                    if (user!=null) {
                        //2、更新用户激活状态
                        user.setActivated(SystemConstants.IS_ACTIVE);
                        user.setFlatId(user.getId());
                        user.setUserType(SystemConstants.REGISTRATION);
                        this.updateById(user);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean userRegister(UserRegisterCondition condition, String registerType) {
        //先根据用户名进行二次判断用户是否存在
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_code",condition.getUserCode());
            User userExist = this.getOne(queryWrapper);
            if (userExist!=null) {
                //用户存在直接抛出异常
                throw new ServiceException(AUTH_USER_ALREADY_EXISTS);
            }
            //先完成实体类的转换
            condition.setUserPassword(MD5.getMd5(condition.getUserPassword(),32));
            User user = new User();
            BeanUtils.copyProperties(condition,user);
            //初始化其他数据，指定用户类型（默认为自注册用户）
            user.setUserType(SystemConstants.REGISTRATION);
            //1.将用户信息存入数据库
            this.save(user);
            /*两种不同的的注册方只有验证码生成规则不一样*/
            switch (registerType){
                //邮箱注册
                case "email" :
                    //2.生成激活码，通过当前系统时间缀进行32位MD5加密
                    String activationCode = MD5.getMd5(String.valueOf(System.currentTimeMillis()), 32);
                    log.info("激活码:{}",activationCode);
                    //3.发送邮件
                    //可以通过定义一个发送邮件的开关，根据不同的环境确定是否需要发送
                    //开发环境和测试环境就可以不用发送，只需要在生产环境
                    if(enableSendEmail){
                        mailService.sendActivationMail(user.getUserCode(),activationCode);
                    }
                    //4.激活码存入Redis中，过期时间为30分钟
                    stringRedisTemplate.opsForValue().set(activeCodeKeyPre+user.getUserCode(),activationCode,30,TimeUnit.MINUTES;
                    break;
                //手机注册
                case "手机" :
                    //生成验证码
                    int randomCode = MD5.getRandomCode();
                    //存入redis
                    stringRedisTemplate.opsForValue().set(activeCodeKeyPre+user.getUserCode(),String.valueOf(randomCode),5,TimeUnit.MINUTES);
                    log.info("短信验证码:{}",randomCode);
                    //与上面的邮件是否发送同理，短信发一条就是一条的钱
                    if (enableSendEmail){
                        //发送验证码
                        smsService.sendMsg(user.getUserCode(),String.valueOf(randomCode));
                    }
                    break;
                default:
                    break;
            }
        }catch (BeansException e){
            throw new ServiceException(ErrorCodeEnum.SYSTEM_EXECUTION_ERROR);
        }
        return true;
    }


    @Override
    public boolean validatePhone(String phoneNum, String code) {
        //1.对比验证码
        String key = "activation:" +phoneNum;
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value!=null&&value.equals(code)){
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.eq("user_code",phoneNum);
            User user = this.getOne(userWrapper);
            if (user!=null){
                //2.更新用户激活状态
                user.setActivated(SystemConstants.IS_ACTIVE);
                user.setFlatId(user.getId());
                user.setUserType(SystemConstants.REGISTRATION);
                this.updateById(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public void getActiveCode(String userCode) {
        //判断当前用户是否已经注册并且状态为未激活
        LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>()
                .lambda()
                .eq(User::getUserCode,userCode)
                .eq(User::getActivated,0);
        int count = this.count(queryWrapper);
        if (count<=0) {
            //如果不存在未被激活的用户则直接抛弃当前不存在的异常
            log.warn("当前用户{} 不存在",userCode);
            throw new ServiceException(AUTH_UNKNOWN);
        }
        //从redis中 查询是否存在该key并有activeCode
        String activeCode = stringRedisTemplate.opsForValue().get(userCode);
        //如果存在则重置过期时间并返回给前端
        if (StringUtils.hasText(activeCode)) {
            //更新过期时间
            stringRedisTemplate.expire(userCode,30,TimeUnit.MINUTES);
        }
        //如果没有则重新生成，并存入redis中
        activeCode = MD5.getMd5(String.valueOf(System.currentTimeMillis()),32);
        stringRedisTemplate.opsForValue().set(userCode,activeCode,30,TimeUnit.MINUTES);
        //最后执行邮件发送
        mailService.sendActivationMail(userCode,activeCode);
    }


}
