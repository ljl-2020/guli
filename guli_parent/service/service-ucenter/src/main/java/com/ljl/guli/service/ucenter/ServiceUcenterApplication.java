/**
 * FileName: ServiceUcenterApplication
 * Author: ljl
 * Date: 2021/7/13 12:49
 * Description:
 * History:
 */


package com.ljl.guli.service.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ljl.guli"})
@EnableDiscoveryClient
public class ServiceUcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUcenterApplication.class,args);
    }
}
