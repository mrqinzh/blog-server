package com.mrqinzh.framework.mq.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class RocketMQConfiguration {

    @Autowired
    private RocketMqAdapter rocketMqAdapter;

    @Bean
    public RocketMQTemplate rocketMQTemplate() {
        return rocketMqAdapter.getTemplateByTopicName(RocketMqConstant.BUSINESS_PRODUCER_GROUP);
    }

    @Configuration
    @Import({RocketMQAutoConfiguration.class})
    static class RocketMqAdapter {

        @Autowired
        private RocketMQMessageConverter rocketMqMessageConverter;

        @Value("${rocketmq.name-server:127.0.0.1:9876}")
        private String nameServer;

        public RocketMQTemplate getTemplateByTopicName(String producerGroup) {
            RocketMQTemplate mqTemplate = new RocketMQTemplate();
            DefaultMQProducer producer = new DefaultMQProducer(producerGroup);
            producer.setNamesrvAddr(nameServer);
            producer.setRetryTimesWhenSendFailed(2);
            producer.setSendMsgTimeout((int) RocketMqConstant.TIMEOUT);
            mqTemplate.setProducer(producer);
            mqTemplate.setMessageConverter(rocketMqMessageConverter.getMessageConverter());
            return mqTemplate;
        }

    }

}
