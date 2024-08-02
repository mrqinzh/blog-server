package com.mrqinzh.framework.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.commons.auth.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

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

    private void sendMessageToClient(SecurityUser user) throws Exception {
        if (user == null) return;
//        WebSocketBean webSocketBean = new WebSocketBean(true, objectMapper.writeValueAsString(new Resp(AppStatus.TOKEN_EXPIRED)));
//        producer.send(WebSocketMessage.TOPIC, new WebSocketMessage(webSocketBean, user.getId(), SecurityProperties.PROJECT_DEVELOPER_ID));
    }

    @Override
    public boolean support(String value) {
//        return value.startsWith(SecurityProperties.TOKEN_CACHE_PREFIX);
        return false;
    }

}
