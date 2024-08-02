package com.mrqinzh.mq.queue;

import com.mrqinzh.mq.message.Message;

import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class DefaultMessageQueue<M extends Message> implements MessageQueue<M> {

    private final ConcurrentHashMap<String, Queue<M>> queueTable;

    public DefaultMessageQueue() {
        queueTable = new ConcurrentHashMap<>();
    }

    public void addMessage(M message) {
        Queue<M> queue = queueTable.get(message.getQueueName());
        if (queue == null) {
            queue = new LinkedBlockingQueue<>();
        }
        queue.add(message);
        queueTable.put(message.getQueueName(), queue);
    }

    public M getMessage(String queueName) {
        return Optional.ofNullable(queueTable.get(queueName)).map(Queue::poll).orElse(null);
    }

    @Override
    public Set<String> getQueues() {
        return queueTable.keySet();
    }
}
