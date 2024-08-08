package com.mrqinzh.auth.handler;

import com.mrqinzh.auth.token.AuthenticatedToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginSuccessHandler {

    void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, AuthenticatedToken token);

}
