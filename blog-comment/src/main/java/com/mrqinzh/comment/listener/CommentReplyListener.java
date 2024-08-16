package com.mrqinzh.comment.listener;

import com.mrqinzh.common.message.CommentReplyMessage;
import com.mrqinzh.framework.message.AbstractMessageListener;

public class CommentReplyListener extends AbstractMessageListener<CommentReplyMessage> {


    @Override
    public void onMessage(CommentReplyMessage message) {
        logger.info("this is CommentReplyListener ...");
        Integer articleId = message.getArticleId();
    }
}
