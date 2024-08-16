package com.mrqinzh.framework.message;

import org.apache.rocketmq.spring.core.RocketMQTemplate;

public class MessageProducer {

    private final RocketMQTemplate rocketMQTemplate;

    public MessageProducer(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    public void sendMessage(AbstractMessage message) {
        rocketMQTemplate.syncSend(MessageConstant.BUSINESS_MESSAGE_TOPIC, message);
    }

}
