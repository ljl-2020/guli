/**
 * FileName: ServiceSmsApplication
 * Author: ljl
 * Date: 2021/7/13 10:05
 * Description:
 * History:
 */


package com.ljl.guli.service.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.ljl.guli"})
@EnableDiscoveryClient
public class ServiceSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class,args);
    }
}
