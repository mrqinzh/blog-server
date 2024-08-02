package com.mrqinzh.webapp.secure.authentication.handler;

import com.mrqinzh.webapp.secure.authentication.token.AuthenticatedToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginSuccessHandler {

    void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, AuthenticatedToken token);

}
