package com.mrqinzh.framework.redis.config;

public interface RedisKeyExpiredHandler {

    void onExpired(String value);

    boolean support(String value);

}
