package com.mrqinzh.mq;

import com.mrqinzh.mq.producer.MessageProducer;
import com.mrqinzh.mq.test.TestListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MqTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MqTestApplication.class, args);

        MessageProducer messageProducer = context.getBean(MessageProducer.class);

        messageProducer.produce(new TestListener.TestMessage());

    }

}
