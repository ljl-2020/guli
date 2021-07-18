/**
 * FileName: InfrastructrueApiGatewayApplication
 * Author: ljl
 * Date: 2021/7/16 15:06
 * Description:
 * History:
 */


package com.ljl.guli.infrastructrue.apigetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class InfrastructrueApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfrastructrueApiGatewayApplication.class,args);
    }
}
