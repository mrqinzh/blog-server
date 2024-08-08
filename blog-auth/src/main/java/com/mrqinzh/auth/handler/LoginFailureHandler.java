package com.mrqinzh.auth.handler;

import com.mrqinzh.auth.exp.AuthException;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginFailureHandler {

    void onLoginFailure(HttpServletRequest request,
                        HttpServletResponse response,
                        AbstractAuthenticationToken<?> credential,
                        AuthException exception);

}
