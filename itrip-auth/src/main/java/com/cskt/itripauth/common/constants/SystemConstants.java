package com.cskt.itripauth.common.constants;

public class SystemConstants {
    /**
     * 用户类型常量(在进行用户注册的时候，会有用户类型的选项，通过定义常量类的形式来处理)
    */
    public static final Integer REGISTRATION = 0; //自注册用户
    public static final Integer WE_CHAT_LOGIN = 1; //微信注册
    public static final Integer QQ_LOGIN = 2;   //QQ注册
    public static final Integer WEI_BO_LOGIN = 3;//微博注册

    /**用户激活状态*/
    public static final Integer NOT_ACTIVE = 0;
    public static final Integer IS_ACTIVE = 1;
}
