package com.mrqinzh.user.auth.filter;


import com.mrqinzh.user.auth.handler.LogoutHandler;
import com.mrqinzh.user.auth.core.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(SecurityProperties.DEFAULT_FILTER_ORDER + 60)
@Component
public class LogoutFilter extends OncePerRequestFilter {

    @Resource
    private LogoutHandler logoutHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!SecurityProperties.LOGOUT_URL.equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        logoutHandler.logout(request, response);

    }

}
