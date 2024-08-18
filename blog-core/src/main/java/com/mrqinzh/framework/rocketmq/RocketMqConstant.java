package com.mrqinzh.framework.rocketmq;

public interface RocketMqConstant {

    // 延迟消息 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h (1-18)

    /**
     * 取消订单时间，实际上30分钟
     */
    int CANCEL_ORDER_DELAY_LEVEL = 16;

    /**
     * 默认发送消息超时时间
     */
    long TIMEOUT = 3000;

    /**
     * 业务消息producer_group
     */
    String BUSINESS_PRODUCER_GROUP = "BUSINESS_PRODUCER_GROUP";


}
