package com.mrqinzh.framework.mq.producer;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.GenericMessage;

public class MessageProducer {

    private RocketMQTemplate rocketMQTemplate;

    public MessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void syncSend(String destination, Object payload) {
        rocketMQTemplate.syncSend(destination, new GenericMessage<>(payload));
    }
}
