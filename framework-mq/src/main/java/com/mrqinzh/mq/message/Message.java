package com.mrqinzh.mq.message;

public interface Message {

    /**
     * 队列名称
     * @return
     */
    String getQueueName();

    /**
     * 消息格式
     * @return
     */
    String getContentType();

    /**
     * 消息类型，即对应的class名称
     * @return
     */
    String getType();

    class NullMessage implements Message {
        @Override
        public String getQueueName() {
            return null;
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public String getType() {
            return null;
        }
    }
}
