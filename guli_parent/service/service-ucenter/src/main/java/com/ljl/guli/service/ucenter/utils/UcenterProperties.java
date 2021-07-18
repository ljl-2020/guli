/**
 * FileName: UcenterProperties
 * Author: ljl
 * Date: 2021/7/14 12:02
 * Description:
 * History:
 */


package com.ljl.guli.service.ucenter.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wx.open")
public class UcenterProperties {

    private String appId;
    private String appSecret;
    private String redirectUri;
}
