package com.mrqinzh.mq.test;

import com.mrqinzh.mq.listener.AbstractMessageListener;
import com.mrqinzh.mq.message.Message;
import org.springframework.stereotype.Component;

@Component
public class TestListener extends AbstractMessageListener<TestListener.TestMessage> {

    @Override
    public void onMessage(TestMessage message) {
        System.out.println("测试listener消息" + getClass().getName());
    }

    public static class TestMessage implements Message {
        @Override
        public String getQueueName() {
            return "test";
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public String getType() {
            return getClass().getName();
        }
    }

}
