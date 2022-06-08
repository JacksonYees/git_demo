package com.cskt.itripauth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cskt.constants.ErrorCodeEnum;
import com.cskt.constants.ReturnResult;
import com.cskt.entity.User;
import com.cskt.vo.UserRegisterCondition;
import com.cskt.itripauth.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

/**
 * 用户相关控制器
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 检查用户是否存在
     * @param name
     * @return
     */
    @ApiOperation(value = "检查用户是否存在",httpMethod = "GET")
    @GetMapping(value = "/ckusr")
    public ReturnResult checkUser(@ApiParam(name = "name",value = "登录账号",required = true,example = "xxx")
                                      @RequestParam String name){
        if (StringUtils.isEmpty(name)){
            //返回参数为空非法字符名
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code",name);
        User user = userService.getOne(queryWrapper);
        if (user!=null){
            //当前用户的数据不为空的时候，效验不通过，用户已经存在
            return ReturnResult.error(ErrorCodeEnum.AUTH_USER_ALREADY_EXISTS);
        }
        return ReturnResult.ok();

    }

    /**
     * 通过正则表达式校验邮箱地址是否符合要求
     * 合法E-mail地址：
     * 1. 必须包含一个并且只有一个符号“@”
     * 2. 第一个字符不得是“@”或者“.”
     * 3. 不允许出现“@.”或者.@
     * 4. 结尾不得是字符“@”或者“.”
     * 5. 允许“@”前的字符中出现“＋”
     * 6. 不允许“＋”在最前面，或者“＋@”
     * @param email
     * @return
     */
    private boolean validEmail(String email){
        String regex="^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return Pattern.compile(regex).matcher(email).find();
    }


    @PutMapping(value = "/activate" ,produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "激活用户",httpMethod = "PUT")
    public ReturnResult doRegister(@RequestBody UserRegisterCondition condition){
        //校验邮箱地址是否符要求
        if (!validEmail(condition.getUserCode())){
            //没有符合邮箱的正则表达式视为非法字符
            return ReturnResult.error(ErrorCodeEnum.AUTH_ILLEGAL_USERCODE);
        }
        boolean result = userService.userRegister(condition);
        if (result){
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }

    /**
     *
     * @param user
     * @param code
     * @return
     */
    @PutMapping(value = "/activate", produces = MediaType.APPLICATION_JSON_VALUE) @ResponseBody
    public ReturnResult activate(@RequestParam String user, @RequestParam String code) {
        //判断参数是否为空
        if (StringUtils.isEmpty(user)||StringUtils.isEmpty(code)) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_PARAMETER_IS_EMPTY);
        }
        boolean activeResult = userService.active(user, code);
        if (activeResult) {
            return ReturnResult.ok();
        } else {
            return ReturnResult.error();
        }
    }

    public boolean validPhone(String phone){
        String regex = "^1[34578]{1}\\d{9}$";
        return Pattern.compile(regex).matcher(phone).find();
    }

    @RequestMapping(value="/doRegisterByPhone",method= RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "添加手机号注册",httpMethod = "POST")
    public ReturnResult doRegisterByPhone(@RequestBody UserRegisterCondition condition){
        //1.手机号格式验证
        if (!this.validPhone(condition.getUserCode())) {
            return ReturnResult.error(ErrorCodeEnum.AUTH_ILLEGAL_USERCODE);
        }
        boolean result = userService.userRegister(condition, "phone");
        if (result) {
            return ReturnResult.ok();
        }
        return ReturnResult.error();
    }

    @RequestMapping(value="/activateByPhone",method=RequestMethod.PUT)
    @ResponseBody
    public ReturnResult activateByPhone(@RequestParam String user,@RequestParam String code){
        try {

            if (userService.validatePhone(user, code)) {
                return ReturnResult.ok();
            } else {
                return ReturnResult.error();
            }
        }catch (Exception e){
            return ReturnResult.error(ErrorCodeEnum.AUTH_ACTIVATE_FAILED);
        }
    }


}
