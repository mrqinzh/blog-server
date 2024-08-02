package com.mrqinzh.mq.handler;

import com.mrqinzh.mq.message.Message;

public interface MessageHandler<M extends Message> {

    void handleMessage(M message);

}
