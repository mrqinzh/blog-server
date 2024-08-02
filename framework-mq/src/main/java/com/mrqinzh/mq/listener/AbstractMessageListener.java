package com.mrqinzh.mq.listener;

import com.mrqinzh.mq.message.Message;

public abstract class AbstractMessageListener<M extends Message> implements MessageListener<M> {
}
