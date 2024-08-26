package com.mrqinzh.gateway.filter.security;

import com.mrqinzh.framework.security.utils.SecurityFrameworkUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(-100)
public class TokenAuthenticationFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = SecurityFrameworkUtils.obtainAuthorization(exchange);
        // 获取不到 token 直接放行
        if (StringUtils.isBlank(token)) {
            return chain.filter(exchange);
        }

        // 有token 进行解析
        return chain.filter(exchange);
    }

}
