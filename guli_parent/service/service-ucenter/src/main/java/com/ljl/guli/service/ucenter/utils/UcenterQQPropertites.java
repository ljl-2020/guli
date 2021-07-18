/**
 * FileName: UcenterQQPropertites
 * Author: ljl
 * Date: 2021/7/14 13:03
 * Description:
 * History:
 */


package com.ljl.guli.service.ucenter.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qq.open")
public class UcenterQQPropertites {

    private String appId;
    private String appSecret;
    private String redirectUri;
}
