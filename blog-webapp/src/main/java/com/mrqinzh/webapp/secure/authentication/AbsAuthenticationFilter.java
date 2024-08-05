package com.mrqinzh.webapp.secure.authentication;

import com.mrqinzh.common.exception.AuthException;
import com.mrqinzh.webapp.secure.authentication.handler.AbstractPrePostAuthHandler;
import com.mrqinzh.webapp.secure.authentication.handler.DefaultLoginHandler;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.secure.authentication.token.AuthenticatedToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public abstract class AbsAuthenticationFilter extends OncePerRequestFilter {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DefaultLoginHandler defaultLoginHandler;
    @Autowired
    private AuthenticationProcessor processor;
    @Autowired
    private List<AbstractPrePostAuthHandler> prePostAuthHandlers;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (!requiredAuthenticationRequest(request)) {
            chain.doFilter(request, response);
            return;
        }
        boolean flag = executeLogin(request, response);
        if (flag) {
            chain.doFilter(request, response);
        }
    }

    protected boolean executeLogin(HttpServletRequest request, HttpServletResponse response) {
        AbstractAuthenticationToken<?> credential = null;
        try {
            credential = createCredentialToken(request);
            // 登录前置处理
            for (AbstractPrePostAuthHandler handler : prePostAuthHandlers) {
                if (!handler.preAuth(request, response, credential)) {
                    return false;
                }
            }
            // 登录认证
            AuthenticatedToken token = processor.auth(credential);
            // 登录后置处理
            for (AbstractPrePostAuthHandler handler : prePostAuthHandlers) {
                if (!handler.postAuth(request, response, token)) {
                    return false;
                }
            }
            // 登录成功
            defaultLoginHandler.onLoginSuccess(request, response, token);
        } catch (AuthException e) {
            logger.error(e.getMessage(), e);
            // 登录失败
            defaultLoginHandler.onLoginFailure(request, response, credential, e);
        }
        return false;
    }

    protected abstract boolean requiredAuthenticationRequest(HttpServletRequest request);

    protected abstract AbstractAuthenticationToken<?> createCredentialToken(HttpServletRequest request);

}
