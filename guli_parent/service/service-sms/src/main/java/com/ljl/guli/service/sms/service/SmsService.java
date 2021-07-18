package com.ljl.guli.service.sms.service;

import java.util.Map;

public interface SmsService {

    //发送短信的方法
    boolean send(Map<String, Object> param, String phone);

}
