/**
 * FileName: ServiceAclApplication
 * Author: ljl
 * Date: 2021/7/17 18:17
 * Description:
 * History:
 */


package com.ljl.guli.service.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.ljl.guli")
public class ServiceAclApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class,args);
    }
}
