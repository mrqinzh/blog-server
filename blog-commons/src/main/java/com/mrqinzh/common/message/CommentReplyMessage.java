package com.mrqinzh.common.message;

import com.mrqinzh.framework.message.AbstractMessage;

public class CommentReplyMessage extends AbstractMessage {

    private Integer articleId;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
