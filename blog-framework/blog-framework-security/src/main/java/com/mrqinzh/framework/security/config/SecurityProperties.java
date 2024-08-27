package com.mrqinzh.framework.security.config;

import com.mrqinzh.framework.common.constant.RpcConstant;
import com.mrqinzh.framework.common.web.WebConstant;

public class SecurityProperties {

    public static final int PROJECT_DEVELOPER_ID = 1;
    public static final int DEFAULT_FILTER_ORDER = 1;
    public static final int DEFAULT_EXPIRE_TIME_SECONDS = 10 * 60; // 单位 second

    public static final String LOGIN_URL = WebConstant.APP_API_PREFIX + "/login";
    public static final String LOGOUT_URL = WebConstant.ADMIN_API_PREFIX + "/logout";
    public static final String SSO_URL = "/sso";
    public static final String USERNAME = "userName";
    public static final String PASSWORD = "userPwd";
    public static final String COOKIE_NAME = "Token";

    public static final String[] withoutAuthApis = {
            WebConstant.APP_API_PREFIX + "/**",
            RpcConstant.RPC_API_PREFIX + "/**"
    };


}
