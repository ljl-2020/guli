/**
 * FileName: AuthGlobaFilter
 * Author: ljl
 * Date: 2021/7/16 17:12
 * Description:
 * History:
 */


package com.ljl.guli.infrastructrue.apigetway.filter;

import com.google.gson.JsonObject;
import com.ljl.guli.common.base.utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;


@Component
public class AuthGlobaFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //判断路径中是否含有/api/**/auth/**
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        AntPathMatcher matcher = new AntPathMatcher();
        if(matcher.match("/api/**/auth/**",path)){
            List<String> token = request.getHeaders().get("token");
            //没有token
            if ( null == token || token.size() == 0  ){
                ServerHttpResponse response = exchange.getResponse();
                return out(response);
            }
            boolean b = JwtUtil.checkToken(token.get(0));
            if(!b){
                ServerHttpResponse response = exchange.getResponse();
                return out(response);
            }
        }
        //放行，将请求继续向下传递
        return chain.filter(exchange);
    }



    //定义过滤器的优先值，越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
    //使用webflux输入请求信息
    private Mono<Void> out(ServerHttpResponse response){
        JsonObject msg = new JsonObject();
        msg.addProperty("success",false);
        msg.addProperty("code",28004);
        msg.addProperty("data","");
        msg.addProperty("message","鉴权失败");
        byte[] bytes = msg.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
