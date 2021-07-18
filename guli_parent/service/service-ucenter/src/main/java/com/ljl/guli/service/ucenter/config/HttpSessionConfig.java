/**
 * FileName: HttpSessionConfig
 * Author: ljl
 * Date: 2021/7/14 13:52
 * Description:
 * History:
 */


package com.ljl.guli.service.ucenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableRedisHttpSession
public class HttpSessionConfig {

    @Bean
    public CookieSerializer cookieSerializer(){
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("JSESSIONID");
        cookieSerializer.setCookiePath("/");
        cookieSerializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        return cookieSerializer;
    }
}
