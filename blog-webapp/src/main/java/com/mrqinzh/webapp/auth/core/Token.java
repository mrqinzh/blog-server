package com.mrqinzh.webapp.auth.core;

import java.io.Serializable;

public interface Token extends Serializable {

    String getUsername(); // 登录账号

    LoginType getLoginType();

    Object getPrincipal();

    boolean isAuthenticated();

}
