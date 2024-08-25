package com.mrqinzh.article.rpc;

public interface CommentApiClient {

    void deleteByTypeId(String articleOrCommentId, Long id);

}
