package com.mrqinzh.framework.security.filter;

import com.mrqinzh.framework.common.security.StoreToken;
import com.mrqinzh.framework.security.utils.AuthenticationTokenCacheUtils;
import com.mrqinzh.framework.security.utils.SecurityFrameworkUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        StoreToken storeToken = AuthenticationTokenCacheUtils.getToken(request);

        if (storeToken != null) {
            SecurityFrameworkUtils.setAuthentication(request, storeToken);
        }

        filterChain.doFilter(request, response);

    }


}
