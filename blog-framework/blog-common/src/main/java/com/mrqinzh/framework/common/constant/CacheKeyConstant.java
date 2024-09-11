package com.mrqinzh.framework.common.constant;

public interface CacheKeyConstant {

    interface SysConfig {
        String CACHE_KEY = "sys_config";
        long EXPIRE_TIME = 60 * 60 * 24 * 7; // 一周
    }

    interface Auth {
        String AUTH_TOKEN_PREFIX = "auth:token:";
    }

}
