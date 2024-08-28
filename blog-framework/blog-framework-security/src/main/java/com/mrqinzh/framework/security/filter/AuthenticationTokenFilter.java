package com.mrqinzh.framework.security.filter;

import com.mrqinzh.framework.common.security.TokenStoreBO;
import com.mrqinzh.framework.security.utils.AuthenticationTokenCacheUtils;
import com.mrqinzh.framework.security.utils.SecurityFrameworkUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        TokenStoreBO storeToken = AuthenticationTokenCacheUtils.getToken(request);

        if (storeToken != null) {
            SecurityFrameworkUtils.setAuthentication(request, storeToken);
        }

        filterChain.doFilter(request, response);

    }


}
