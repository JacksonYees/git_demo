package com.cskt.itripauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cskt.entity.User;
import com.cskt.vo.UserRegisterCondition;


public interface UserService extends IService<User> {
    /**
     * 用户注册
     * @param condition 注册数据
     * @return
     */
    boolean userRegister(UserRegisterCondition condition);

    /**
     * 激活用户
     * @param userCode 邮箱地址
     * @param code 激活码
     * @return
     */
    boolean active(String userCode,String code);

    /**
     * 用户注册
     * @param condition 注册数据
     * @param registerType 注册类型
     * @return
     */
    boolean userRegister(UserRegisterCondition condition,String registerType);

    /**
     * 短信验证
     * @param phoneNum 手机号码
     * @param code 验证码
     * @return  true表示验证成功，false表示验证失败
     */
    boolean validatePhone(String phoneNum, String code);

    /**
     * 二次获取激活码
     * @param userCode
     */
    void getActiveCode(String userCode);
}
