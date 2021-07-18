/**
 * FileName: Swagger2Config
 * Author: ljl
 * Date: 2021/3/20 19:50
 * Description: API Swagger2使用
 * History:
 */


package com.ljl.guli.service.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    @Bean
    public Docket adminApiConfig(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("前端接口文档")
                .description("此文档描述了前端界面接口的API")
                .version("1.0.0")
                .contact(new Contact("luojl","http://www.liangjl.online","2830429738@qq.com"))
                .build();

    }
    private ApiInfo adminApiInfo(){
        return new ApiInfoBuilder()
                .title("后端接口文档")
                .description("此文档描述了后端管理员接口的API")
                .version("1.0.0")
                .contact(new Contact("luojl","http://www.liangjl.online","2830429738@qq.com"))
                .build();

    }
}
