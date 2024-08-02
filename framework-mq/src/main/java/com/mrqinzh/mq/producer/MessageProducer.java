package com.mrqinzh.mq.producer;

import com.mrqinzh.mq.message.Message;

import java.util.concurrent.TimeUnit;

public interface MessageProducer<M extends Message> {

    void produce(M message);

    void produce(M message, long delay, TimeUnit timeUnit);

}
