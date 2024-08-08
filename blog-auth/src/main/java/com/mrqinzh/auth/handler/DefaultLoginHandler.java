package com.mrqinzh.auth.handler;

import com.mrqinzh.auth.RedirectStrategy;
import com.mrqinzh.auth.event.AuthFailureEvent;
import com.mrqinzh.auth.event.AuthSuccessEvent;
import com.mrqinzh.auth.exp.AuthException;
import com.mrqinzh.auth.session.SessionManager;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.token.AuthenticatedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * login处理器
 */
@Component
public class DefaultLoginHandler implements LoginSuccessHandler, LoginFailureHandler {

    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private RedirectStrategy redirectStrategy;

    @Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, AuthenticatedToken token) {
        publisher.publishEvent(new AuthSuccessEvent(token));
        sessionManager.start(request, response, token);
        redirectStrategy.redirect(request, response);
    }

    @Override
    public void onLoginFailure(HttpServletRequest request,
                               HttpServletResponse response,
                               AbstractAuthenticationToken<?> credential,
                               AuthException exception) {
        publisher.publishEvent(new AuthFailureEvent(exception, credential));
        redirectStrategy.redirect(request, response);
    }

}
