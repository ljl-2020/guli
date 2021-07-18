/**
 * FileName: MybatisPlusConfig
 * Author: ljl
 * Date: 2021/3/20 19:07
 * Description: 配置文件
 * History:
 */


package com.ljl.guli.service.base.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@MapperScan("com.ljl.guli.service.*.mapper")
public class MybatisPlusConfig {

    //分页
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }


}
