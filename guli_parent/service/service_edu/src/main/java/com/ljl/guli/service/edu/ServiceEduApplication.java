/**
 * FileName: ServiceEduApplication
 * Author: ljl
 * Date: 2021/3/20 19:13
 * Description: 主类
 * History:
 */


package com.ljl.guli.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ljl.guli"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class,args);
    }
}
