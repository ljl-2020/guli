/**
 * FileName: ServiceOssApplication
 * Author: ljl
 * Date: 2021/5/15 11:28
 * Description:
 * History:
 */


package com.ljl.guli.service.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.ljl.guli")
@EnableDiscoveryClient
public class ServiceOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceOssApplication.class,args);
    }
}
