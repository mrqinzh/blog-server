package com.mrqinzh.framework.redis.event;

import com.mrqinzh.framework.redis.handler.RedisKeyExpiredHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
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
public class RedisKeyExpiredListener extends KeyExpirationEventMessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisKeyExpiredListener.class);

    @Autowired
    private ObjectProvider<List<RedisKeyExpiredHandler>> handlersProvider;

    public RedisKeyExpiredListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        LOGGER.info("expire key ==> {}, pattern => {}", message, pattern);
        String value = new String(message.getBody(), StandardCharsets.UTF_8);

        handlersProvider.ifAvailable(handlers -> handlers.stream().filter(h -> h.support(value)).forEach(h -> h.onExpired(value)));

    }

}
