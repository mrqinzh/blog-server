package com.mrqinzh.mq.config;

import com.mrqinzh.mq.consumer.DefaultMessageConsumer;
import com.mrqinzh.mq.consumer.MessageConsumer;
import com.mrqinzh.mq.handler.DefaultMessageHandler;
import com.mrqinzh.mq.handler.MessageHandler;
import com.mrqinzh.mq.producer.DefaultMQMessageProducer;
import com.mrqinzh.mq.producer.MessageProducer;
import com.mrqinzh.mq.queue.DefaultMessageQueue;
import com.mrqinzh.mq.queue.MessageQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQAutoConfiguration {

    @Bean
    public MessageQueue messageQueue() {
        return new DefaultMessageQueue();
    }

    @Bean
    public MessageHandler messageHandler() {
        return new DefaultMessageHandler();
    }

    @Bean
    public MessageConsumer messageConsumer(MessageQueue messageQueue, MessageHandler messageHandler) {
        return new DefaultMessageConsumer(messageQueue, messageHandler);
    }

    @Bean
    public MessageProducer messageProducer(MessageQueue messageQueue) {
        return new DefaultMQMessageProducer(messageQueue);
    }

}
