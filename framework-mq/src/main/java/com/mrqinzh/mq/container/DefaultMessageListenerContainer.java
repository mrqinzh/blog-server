package com.mrqinzh.mq.container;

import com.mrqinzh.mq.listener.MessageListener;
import com.mrqinzh.mq.message.Message;

import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultMessageListenerContainer {

    private final Map<String, MessageListener<Message>> listeners = new ConcurrentHashMap<>();
    public static final DefaultMessageListenerContainer instance = new DefaultMessageListenerContainer();

    private DefaultMessageListenerContainer() {
    }

    public void addListener(MessageListener<? extends Message> listener) {
        ParameterizedType type = (ParameterizedType) listener.getClass().getGenericSuperclass();
        Class<?> clazz = (Class<?>) type.getActualTypeArguments()[0];
        listeners.put(clazz.getName(), (MessageListener<Message>) listener);
    }

    public static Map<String, MessageListener<Message>> getListeners() {
        return instance.listeners;
    }
}
