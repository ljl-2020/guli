/**
 * FileName: VodProperties
 * Author: ljl
 * Date: 2021/6/16 12:10
 * Description:
 * History:
 */


package com.ljl.guli.service.vod.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aliyun.vod")
public class VodProperties {

    private String keyid;
    private String keysecret;
    private String workflowId;
    private String templateGroupId;
}
