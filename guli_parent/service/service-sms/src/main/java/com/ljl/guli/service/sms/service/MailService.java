package com.ljl.guli.service.sms.service;

public interface MailService {

    boolean sendSimpleMail(String toPeo,String title,String content);
}
