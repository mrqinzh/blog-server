package com.mrqinzh.mq.queue;

import com.mrqinzh.mq.message.Message;

import java.util.Set;

public interface MessageQueue<M extends Message> {

    void addMessage(M message);

    M getMessage(String queueName);

    Set<String> getQueues();
}
