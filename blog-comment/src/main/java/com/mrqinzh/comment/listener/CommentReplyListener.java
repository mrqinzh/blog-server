package com.mrqinzh.comment.listener;

import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.framework.common.constant.MessageConstant;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = MessageConstant.Comment.COMMENT_REPLY_TOPIC, consumerGroup = MessageConstant.Comment.COMMENT_REPLY_TOPIC)
public class CommentReplyListener implements RocketMQListener<CommentReqVO> {


    @Override
    public void onMessage(CommentReqVO message) {
//        logger.info("this is CommentReplyListener ...");
        Long articleId = message.getArticleId();
    }
}
