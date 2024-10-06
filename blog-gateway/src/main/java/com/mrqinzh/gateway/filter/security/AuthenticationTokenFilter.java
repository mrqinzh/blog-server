package com.mrqinzh.gateway.filter.security;

import com.mrqinzh.framework.common.security.LoginUser;
import com.mrqinzh.gateway.utils.SecurityFrameworkUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * gateway模块 认证过滤器
 * 将请求头中的token信息，转换成登录用户信息
 */
@Component
@Order(-100)
public class AuthenticationTokenFilter implements GlobalFilter {

    private static final LoginUser EMPTY_USER = new LoginUser();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 先移除设置
        SecurityFrameworkUtils.removeLoginUser(exchange);
        String token = SecurityFrameworkUtils.obtainAuthorization(exchange);
        if (StringUtils.isBlank(token)) {
            return chain.filter(exchange);
        }
        // TODO 提前获取 loginUser 并设置到 requestHeader 中
        return getLoginUser(exchange, token).defaultIfEmpty(EMPTY_USER).flatMap(user -> {
            // 用户不存在，放行
            if (user == EMPTY_USER) {
                return chain.filter(exchange);
            }
            // 设置登录用户
            SecurityFrameworkUtils.setLoginUser(exchange, user);
            // 将user设置到 login-user 请求头中去
            ServerWebExchange newExchange = exchange.mutate().request(builder -> SecurityFrameworkUtils.setLoginUserHeader(builder, user)).build();
            return chain.filter(newExchange);
        });
    }

    private Mono<LoginUser> getLoginUser(ServerWebExchange exchange, String token) {
        // TODO 根据 token 获取 loginUser
        return Mono.empty();
    }

}
