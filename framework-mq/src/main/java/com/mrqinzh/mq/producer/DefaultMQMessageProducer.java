package com.mrqinzh.mq.producer;

import com.mrqinzh.mq.message.Message;
import com.mrqinzh.mq.queue.MessageQueue;

import java.util.concurrent.TimeUnit;

public class DefaultMQMessageProducer<M extends Message> implements MessageProducer<M> {

    private final MessageQueue<M> messageQueue;

    public DefaultMQMessageProducer(MessageQueue<M> messageQueue) {
        this.messageQueue = messageQueue;
    }

    @Override
    public void produce(M message) {
        messageQueue.addMessage(message);
    }

    @Override
    public void produce(M message, long delay, TimeUnit timeUnit) {

    }
}
