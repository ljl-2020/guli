/**
 * FileName: WeiXinPayProperties
 * Author: ljl
 * Date: 2021/7/15 12:59
 * Description:
 * History:
 */


package com.ljl.guli.service.trade.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix="wx.pay")
public class WeiXinProperties {
    private String appId;
    private String partner;
    private String partnerKey;
    private String notifyUrl;
}

