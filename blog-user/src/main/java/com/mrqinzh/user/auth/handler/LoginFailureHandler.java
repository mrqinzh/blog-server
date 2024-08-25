package com.mrqinzh.user.auth.handler;

import com.mrqinzh.user.auth.exp.AuthException;
import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginFailureHandler {

    void onLoginFailure(HttpServletRequest request,
                        HttpServletResponse response,
                        AbstractAuthenticationToken<?> credential,
                        AuthException exception);

}
