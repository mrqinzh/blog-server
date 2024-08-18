package com.mrqinzh.framework.redis;

public interface RedisKeyExpiredHandler {

    void onExpired(String value);

    boolean support(String value);

}
