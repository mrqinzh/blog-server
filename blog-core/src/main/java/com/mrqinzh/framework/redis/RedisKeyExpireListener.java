package com.mrqinzh.framework.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrqinzh.framework.utils.RedisUtil;
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
    private RedisUtil redisUtil;
    @Autowired
    private ObjectMapper objectMapper;
//    @Autowired
//    private List<RedisKeyExpiredHandler> handlers;

    public RedisKeyExpireListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("message ==> key " + message);
        String value = new String(message.getBody(), StandardCharsets.UTF_8);
        System.out.println("partten " + value); // __keyevent@*__:expired

//        for (RedisKeyExpiredHandler handler : handlers) {
//            if (handler.support(new String(message.getBody(), StandardCharsets.UTF_8))) {
//                try {
//                    handler.handle(value);
//                } catch (Exception e) {
//                    logger.error(handler.getClass() + " error !", e);
//                }
//            }
//        }
    }

}
