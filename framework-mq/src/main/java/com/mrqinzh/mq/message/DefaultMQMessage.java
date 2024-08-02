package com.mrqinzh.mq.message;

public class DefaultMQMessage implements Message {

    private String queueName;
    private String contentType;

    private Class<?> type;
    private Object data;


    @Override
    public String getQueueName() {
        return queueName;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public String getType() {
        return type.getName();
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
