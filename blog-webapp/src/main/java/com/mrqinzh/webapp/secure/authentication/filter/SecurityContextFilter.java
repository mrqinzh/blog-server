package com.mrqinzh.webapp.secure.authentication.filter;

import com.mrqinzh.webapp.secure.authentication.context.AuthenticationContextHolder;
import com.mrqinzh.webapp.secure.authentication.session.SessionManager;
import com.mrqinzh.webapp.secure.authentication.token.AuthenticatedToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(SecurityProperties.DEFAULT_FILTER_ORDER + 51)
@Component
public class SecurityContextFilter extends OncePerRequestFilter {

    @Autowired
    private SessionManager sessionManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenId = sessionManager.getTokenId(request);
        if (StringUtils.isBlank(tokenId)) {
            filterChain.doFilter(request, response);
            return;
        }
        AuthenticatedToken token = sessionManager.getToken(tokenId);
        if (token != null) {
            AuthenticationContextHolder.getContext().setToken(token);
            try {
                filterChain.doFilter(request, response);
            } finally {
                AuthenticationContextHolder.clearContext();
            }
        }

    }
}
