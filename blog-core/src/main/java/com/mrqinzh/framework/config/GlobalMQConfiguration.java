package com.mrqinzh.framework.config;

import com.mrqinzh.framework.message.MessageProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalMQConfiguration {


    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        return new RocketMQTemplate();
    }

    @Bean
    public MessageProducer messageProducer(RocketMQTemplate rocketMQTemplate) {
        return new MessageProducer(rocketMQTemplate);
    }

}
