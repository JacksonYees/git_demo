package com.cskt.itripauth.service.impl;

import com.cskt.itripauth.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    private MailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void sendActivationMail(String mailTo, String activationCode) {
        //初始化一个simpleMailMessage
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("爱旅行注册激活码");
        simpleMailMessage.setFrom(emailFrom);
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setText("您的激活码是："+activationCode);
        mailSender.send(simpleMailMessage);
    }

}
