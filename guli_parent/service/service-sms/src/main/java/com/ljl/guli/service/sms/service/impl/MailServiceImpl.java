/**
 * FileName: MailServiceImpl
 * Author: ljl
 * Date: 2021/7/13 11:44
 * Description:
 * History:
 */


package com.ljl.guli.service.sms.service.impl;

import com.ljl.guli.service.sms.service.MailService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MailServiceImpl  implements MailService {

    @Value("${spring.mail.username}")
    private String fromPeo;

    @Resource
    private JavaMailSender mailSender;

    public boolean sendSimpleMail(String toPeo,String title,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromPeo);//是谁发送的
        message.setTo(toPeo);//发送给谁
        message.setSubject(title);//标题
        message.setText(content);//内容
//        System.out.println(from);
        mailSender.send(message);
        log.info("邮件发送成功！");
        return true;
    }
}
