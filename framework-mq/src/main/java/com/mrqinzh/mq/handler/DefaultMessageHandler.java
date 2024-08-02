package com.mrqinzh.mq.handler;

import com.mrqinzh.mq.container.DefaultMessageListenerContainer;
import com.mrqinzh.mq.message.Message;

public class DefaultMessageHandler<M extends Message> implements MessageHandler<M> {

    @Override
    public final void handleMessage(M message) {
        DefaultMessageListenerContainer.getListeners().get(message.getType()).onMessage(message);
    }

}
