package com.mrqinzh.framework.cache;

import com.mrqinzh.framework.utils.RedisUtil;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationCache {

    public static final String PREFIX = "auth:";

    public void put(String key, Object value) {
        RedisUtil.set(getKey(key), value, 10*60);
    }

    public void put(String key, Object value, long expiredTime) {
        RedisUtil.set(getKey(key), value, expiredTime);
    }

    public Object get(String key) {
        return RedisUtil.get(getKey(key));
    }

    public boolean hasKey(String key) {
        return RedisUtil.hasKey(getKey(key));
    }

    public boolean delete(String key) {
        return RedisUtil.expire(getKey(key), 0);
    }

    private String getKey(String key) {
        return PREFIX + key;
    }

}
