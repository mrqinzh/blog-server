package com.mrqinzh.mq.listener;

import com.mrqinzh.mq.message.Message;

public interface MessageListener<M extends Message> {

    void onMessage(M message);

}
