package com.mrqinzh.webapp.auth.handler;

import com.mrqinzh.webapp.auth.exp.AuthException;
import com.mrqinzh.webapp.auth.token.AbstractAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginFailureHandler {

    void onLoginFailure(HttpServletRequest request,
                        HttpServletResponse response,
                        AbstractAuthenticationToken<?> credential,
                        AuthException exception);

}
