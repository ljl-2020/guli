/**
 * FileName: ServiceStatisticApplication
 * Author: ljl
 * Date: 2021/7/17 11:11
 * Description:
 * History:
 */


package com.ljl.guli.service.statistics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.ljl.guli")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling //定时任务，spring task
public class ServiceStatisticApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceStatisticApplication.class,args);
    }
}
