package com.mrqinzh.auth;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SecurityProperties {

    public static final int PROJECT_DEVELOPER_ID = 1;
    public static final int DEFAULT_FILTER_ORDER = 1;
    public static final int DEFAULT_EXPIRE_TIME_SECONDS = 10 * 60; // 单位 second

    public static final String TOKEN_CACHE_PREFIX = "authentication:";
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String SSO_URL = "/sso";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "userPwd";
    public static final String COOKIE_NAME = "token";

    public static final Set<String> accessApisWithoutAuth = new HashSet<>();
    // todo 待调整
    public static final String[] systemWhiteApis = {
            "/static/**", "/files/**", "/*.html", "/*.ico", "/img/**",       // 静态资源
            "/api/websocket/**",               // websocket
            LOGIN_URL, "/login/**",            // 登录相关
            "/**/list", "/**/page", "/**/byKeys",
            "/article/{articleId}",
            "/comment/message-list", "/comment/add", "/comment/{idType}/{id}",
            "/chat/**",
            "/test/**"
    };
    static {
        accessApisWithoutAuth.addAll(Arrays.asList(systemWhiteApis));
    }

}
