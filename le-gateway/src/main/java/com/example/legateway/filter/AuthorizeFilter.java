package com.example.legateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author whl
 * @Description
 * @date 2023/9/14 23:37
 */
@Slf4j
@Order(0)
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * todo 网关权限验证（临时demo），需要优化
         * 可以通过配置类去决定开没开启过滤
         * 通过配置文件决定那些不需要验证token
         */
        ServerHttpResponse response = exchange.getResponse();
        String headerToken = exchange.getRequest().getHeaders().getFirst("token");
        if (headerToken == null) {
//            return unauthorized(resp, "没有携带Token信息！");
            log.error("没有携带Token信息!");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
//        Claims claims = SecurityUtil.getClaims(headerToken.replace("bearer ",""));
        String claims = headerToken;
        if (claims == null) {
//            return unauthorized(resp, "token已过期或验证不正确！");
            log.error("token已过期或验证不正确!");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        return chain.filter(exchange);
    }
}
