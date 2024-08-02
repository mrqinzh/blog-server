package com.mrqinzh.mq.consumer;

import com.mrqinzh.mq.message.Message;

public interface MessageConsumer<M extends Message> {

    void consume(String queue);

}
