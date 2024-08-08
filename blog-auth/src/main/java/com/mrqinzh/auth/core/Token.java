package com.mrqinzh.auth.core;

public interface Token {

    String getUsername(); // 登录账号

    LoginType getLoginType();

    Object getPrincipal();

    boolean isAuthenticated();

}
