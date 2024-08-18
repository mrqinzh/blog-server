package com.mrqinzh.webapp.auth.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrqinzh.framework.cache.AuthenticationCache;
import com.mrqinzh.framework.redis.RedisKeyExpiredHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenExpireHandler implements RedisKeyExpiredHandler {

    @Autowired
    private ObjectMapper objectMapper;
//    @Autowired
//    private GlobalMessageProducer producer;
//    @Autowired
//    private SecurityService securityService;

    @Override
    public void onExpired(String value) {
        System.out.println(value);
//        String username = value.substring(SecurityProperties.TOKEN_CACHE_PREFIX.length() +
//                UUID.randomUUID().toString().replaceAll("-", "").length());
//        SecurityUser user = securityService.loadSecurityUserFromDb(username);
        // 告诉前端token过期
//        this.sendMessageToClient(user);

        // 。。。。。。
    }


    @Override
    public boolean support(String value) {
        return value.startsWith(AuthenticationCache.PREFIX);
    }

}
