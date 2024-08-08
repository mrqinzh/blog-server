package com.mrqinzh.framework.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void handle(String value) throws Exception {
        // todo 后续将token转为jwt方式保存
//        String username = value.substring(SecurityProperties.TOKEN_CACHE_PREFIX.length() +
//                UUID.randomUUID().toString().replaceAll("-", "").length());
//        SecurityUser user = securityService.loadSecurityUserFromDb(username);
        // 告诉前端token过期
//        this.sendMessageToClient(user);

        // 。。。。。。
    }


    @Override
    public boolean support(String value) {
//        return value.startsWith(SecurityProperties.TOKEN_CACHE_PREFIX);
        return false;
    }

}
