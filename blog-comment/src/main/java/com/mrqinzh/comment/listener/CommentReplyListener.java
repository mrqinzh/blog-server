package com.mrqinzh.comment.listener;

import com.mrqinzh.common.constant.MessageConstant;
import com.mrqinzh.common.domain.vo.comment.CommentVo;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = MessageConstant.CommentMessage.COMMENT_REPLY_TOPIC, consumerGroup = MessageConstant.CommentMessage.COMMENT_REPLY_TOPIC)
public class CommentReplyListener implements RocketMQListener<CommentVo> {


    @Override
    public void onMessage(CommentVo message) {
//        logger.info("this is CommentReplyListener ...");
        Integer articleId = message.getArticleId();
    }
}
