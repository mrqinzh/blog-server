package com.mrqinzh.webapp.secure.authentication.filter;

import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.webapp.secure.authentication.RedirectStrategy;
import com.mrqinzh.webapp.secure.authentication.SecurityProperties;
import com.mrqinzh.webapp.secure.authentication.context.AuthenticationContextHolder;
import com.mrqinzh.webapp.secure.authentication.context.AuthenticationContextUtils;
import com.mrqinzh.webapp.secure.authentication.session.SessionManager;
import com.mrqinzh.webapp.secure.authentication.token.AuthenticatedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(SecurityProperties.DEFAULT_FILTER_ORDER + 60)
@Component
public class LogoutFilter extends OncePerRequestFilter {

    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private RedirectStrategy redirectStrategy;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!SecurityProperties.LOGOUT_URL.equals(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }
        AuthenticatedToken token = AuthenticationContextUtils.getAuthenticatedToken();
        sessionManager.expire(token);
        AuthenticationContextHolder.clearContext();
        redirectStrategy.redirect(request, response, new Resp(AppStatus.LOGOUT));

    }

}
