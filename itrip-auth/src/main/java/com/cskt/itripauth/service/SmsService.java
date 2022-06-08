package com.cskt.itripauth.service;

/**
 *短信service层接口
 */
public interface SmsService {
    /**
     * 用户发送短信
     * @param to 短信发送给谁
     * @param code 验证码
     */
    void sendMsg(String to,String code);


}
