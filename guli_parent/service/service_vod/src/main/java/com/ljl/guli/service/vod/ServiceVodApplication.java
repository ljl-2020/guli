/**
 * FileName: ServiceVodApplication
 * Author: ljl
 * Date: 2021/6/16 11:58
 * Description:
 * History:
 */


package com.ljl.guli.service.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)//取消数据源自动配置
@ComponentScan("com.ljl.guli")
@EnableDiscoveryClient
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class,args);
    }
}
