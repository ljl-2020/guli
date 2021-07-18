/**
 * FileName: SmsServiceImpl
 * Author: ljl
 * Date: 2021/7/13 10:36
 * Description:
 * History:
 */


package com.ljl.guli.service.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.client.utils.StringUtils;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.ljl.guli.service.base.exception.MyException;
import com.ljl.guli.service.sms.service.SmsService;
import com.ljl.guli.service.sms.util.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsProperties smsProperties;
    //发送短信的方法
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", smsProperties.getKeyId(), smsProperties.getKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        //设置相关固定的参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        //设置发送相关的参数
        request.putQueryParameter("PhoneNumbers",phone); //手机号
//        request.putQueryParameter("SignName","我的谷粒在线教育网站"); //申请阿里云 签名名称
//        request.putQueryParameter("TemplateCode","SMS_180051135"); //申请阿里云 模板code
        request.putQueryParameter("SignName","学习短信发送方式"); //申请阿里云 签名名称
        request.putQueryParameter("TemplateCode","SMS_210076548"); //申请阿里云 模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递

        try {
            //最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        }catch(Exception e) {
            return false;
        }

    }


}
