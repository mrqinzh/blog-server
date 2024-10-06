package com.mrqinzh.framework.redis.handler;

public interface RedisKeyExpiredHandler {

    void onExpired(String value);

    boolean support(String value);

}
