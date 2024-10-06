package com.mrqinzh.framework.security.filter;

import cn.hutool.core.util.StrUtil;
import com.mrqinzh.framework.common.security.LoginUser;
import com.mrqinzh.framework.common.security.StoreToken;
import com.mrqinzh.framework.common.utils.JsonUtils;
import com.mrqinzh.framework.security.utils.AuthenticationTokenCacheUtils;
import com.mrqinzh.framework.security.utils.SecurityFrameworkUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 情况一，基于 header[login-user] 获得用户，例如来自其他服务转发的请求 feign
        LoginUser user = buildLoginUserByHeader(request);
        if (user != null) {
            SecurityFrameworkUtils.setAuthentication(request, user);
        }
        // 情况二，基于 token 获得用户，例如说来自 Gateway
        user = buildLoginUserByToken(request);
        if (user != null) {
            SecurityFrameworkUtils.setAuthentication(request, user);
        }

        filterChain.doFilter(request, response);

    }

    private LoginUser buildLoginUserByToken(HttpServletRequest request) {
        StoreToken token = AuthenticationTokenCacheUtils.getToken(request);
        return token == null ? null : token.getUser();
    }

    private LoginUser buildLoginUserByHeader(HttpServletRequest request) {
        String loginUserStr = request.getHeader(SecurityFrameworkUtils.LOGIN_USER_HEADER);
        if (StrUtil.isEmpty(loginUserStr)) {
            return null;
        }
        try {
            loginUserStr = URLDecoder.decode(loginUserStr, StandardCharsets.UTF_8); // 解码，解决中文乱码问题
            return JsonUtils.parseObject(loginUserStr, LoginUser.class);
        } catch (Exception ex) {
            log.error("[buildLoginUserByHeader][解析 LoginUser({}) 发生异常]", loginUserStr, ex);  ;
            throw ex;
        }
    }


}
