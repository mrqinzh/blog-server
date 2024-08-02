package com.mrqinzh.framework.redis;

public interface RedisKeyExpiredHandler {

    void handle(String value) throws Exception;

    boolean support(String value);

}
