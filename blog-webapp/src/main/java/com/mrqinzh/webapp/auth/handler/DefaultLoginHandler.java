package com.mrqinzh.webapp.auth.handler;

import com.mrqinzh.webapp.auth.context.AuthenticationContextHolder;
import com.mrqinzh.webapp.auth.event.AuthFailureEvent;
import com.mrqinzh.webapp.auth.event.AuthSuccessEvent;
import com.mrqinzh.webapp.auth.exp.AuthException;
import com.mrqinzh.webapp.auth.session.SessionManager;
import com.mrqinzh.webapp.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.auth.token.AuthenticatedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * login处理器
 */
@Component
public class DefaultLoginHandler implements LoginSuccessHandler, LoginFailureHandler, LogoutHandler {

    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private SessionManager sessionManager;

    @Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, AuthenticatedToken token) {
        publisher.publishEvent(new AuthSuccessEvent(token));
        sessionManager.start(request, response, token);

        writeSuccessResponse(request, response, token);
    }

    @Override
    public void onLoginFailure(HttpServletRequest request,
                               HttpServletResponse response,
                               AbstractAuthenticationToken<?> credential,
                               AuthException exception) {
        publisher.publishEvent(new AuthFailureEvent(exception, credential));
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        AuthenticatedToken token = sessionManager.getToken(request);
        sessionManager.expire(token);
        AuthenticationContextHolder.clearContext();
        writeLogoutResponse(request, response, token);
    }
}
