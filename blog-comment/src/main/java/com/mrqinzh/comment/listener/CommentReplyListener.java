package com.mrqinzh.comment.listener;

import com.mrqinzh.comment.domain.dto.CommentReqDTO;
import com.mrqinzh.framework.common.constant.MessageConstant;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = MessageConstant.Comment.COMMENT_REPLY_TOPIC, consumerGroup = MessageConstant.Comment.COMMENT_REPLY_TOPIC)
public class CommentReplyListener implements RocketMQListener<CommentReqDTO> {


    @Override
    public void onMessage(CommentReqDTO message) {
//        logger.info("this is CommentReplyListener ...");
        Long articleId = message.getArticleId();
    }
}
