package com.mrqinzh.mq.listener;

import com.mrqinzh.mq.message.Message;

public interface MessageListenerRegistrar {

    void register(MessageListener<Message> listener);

}
