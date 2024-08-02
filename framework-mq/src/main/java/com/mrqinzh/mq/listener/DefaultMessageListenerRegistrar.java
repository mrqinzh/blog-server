package com.mrqinzh.mq.listener;

import com.mrqinzh.mq.container.DefaultMessageListenerContainer;
import com.mrqinzh.mq.message.Message;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class DefaultMessageListenerRegistrar implements MessageListenerRegistrar, BeanPostProcessor {

    @Override
    public void register(MessageListener<Message> listener) {
        DefaultMessageListenerContainer.instance.addListener(listener);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MessageListener) {
            MessageListener<Message> messageListener = (MessageListener<Message>) bean;
            register(messageListener);
        }
        return bean;
    }

}
