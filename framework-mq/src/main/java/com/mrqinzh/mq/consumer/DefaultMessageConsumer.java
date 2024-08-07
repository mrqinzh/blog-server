package com.mrqinzh.mq.consumer;

import com.mrqinzh.mq.handler.MessageHandler;
import com.mrqinzh.mq.message.DefaultMQMessage;
import com.mrqinzh.mq.message.Message;
import com.mrqinzh.mq.queue.MessageQueue;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DefaultMessageConsumer<M extends Message> implements MessageConsumer<M> {

    private final MessageQueue<M> messageQueue;
    private final MessageHandler<M> handler;

    private final ExecutorService pool;

    public DefaultMessageConsumer(MessageQueue<M> messageQueue, MessageHandler<M> handler) {
        this.messageQueue = messageQueue;
        this.handler = handler;
        pool = Executors.newFixedThreadPool(5);
        start();
    }

    protected void start() {
        // todo need block
//        pool.execute(() -> {
//            while (true) {
//                Set<String> queues = messageQueue.getQueues();
//                if (queues == null || queues.isEmpty()) {
//                    continue;
//                }
//                queues.forEach(this::consume);
//            }
//        });

    }

    @Override
    public void consume(String queue) {
        M message = messageQueue.getMessage(queue);
        if (message == null) {
            return;
        }
        handler.handleMessage(message);
    }
}
