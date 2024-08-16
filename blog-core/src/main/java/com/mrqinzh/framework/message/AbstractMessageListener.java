package com.mrqinzh.framework.message;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RocketMQMessageListener(topic = MessageConstant.BUSINESS_MESSAGE_TOPIC, consumerGroup = MessageConstant.BUSINESS_MESSAGE_TOPIC)
public abstract class AbstractMessageListener<T extends AbstractMessage> implements RocketMQListener<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

}
