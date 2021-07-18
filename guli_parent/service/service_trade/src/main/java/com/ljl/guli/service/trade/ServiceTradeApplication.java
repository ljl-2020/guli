/**
 * FileName: ServiceTradeApplication
 * Author: ljl
 * Date: 2021/7/14 14:37
 * Description:
 * History:
 */


package com.ljl.guli.service.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ljl.guli"})
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceTradeApplication.class,args);
    }
}
