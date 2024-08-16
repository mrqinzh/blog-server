package com.mrqinzh.framework.message;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.Serializable;

public abstract class AbstractMessage implements Message<Object>, Serializable {

    @Override
    public Object getPayload() {
        return this;
    }

    @Override
    public MessageHeaders getHeaders() {
        return null;
    }
}
