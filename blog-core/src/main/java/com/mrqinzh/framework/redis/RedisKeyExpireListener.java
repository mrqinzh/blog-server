package com.mrqinzh.framework.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * redis Key过期事件监听器
 */
@Component
public class RedisKeyExpireListener extends KeyExpirationEventMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RedisKeyExpireListener.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired(required = false)
    private List<RedisKeyExpiredHandler> handlers;

    public RedisKeyExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        logger.info("message ==> key " + message);
        String value = new String(message.getBody(), StandardCharsets.UTF_8);
        logger.info("partten " + value); // __keyevent@*__:expired

        if (CollectionUtils.isNotEmpty(handlers)) {
            handlers.stream().filter(h -> h.support(value)).forEach(h -> h.onExpired(value));
        }

    }

}
