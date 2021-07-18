/**
 * FileName: OssPropertity
 * Author: ljl
 * Date: 2021/5/15 11:36
 * Description:
 * History:
 */


package com.ljl.guli.service.oss.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssPropertity {
    private String endpoint;
    private String keyid;
    private String keysecret;
    private String bucketname;
}
