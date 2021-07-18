/**
 * FileName: SmsProperties
 * Author: ljl
 * Date: 2021/7/13 10:20
 * Description:
 * History:
 */


package com.ljl.guli.service.sms.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "aliyun.sms")
@Component
public class SmsProperties {

    private String regionId;

    private String keyId;
    private String keySecret;
    private String templateCode;
    private String signName;

}
