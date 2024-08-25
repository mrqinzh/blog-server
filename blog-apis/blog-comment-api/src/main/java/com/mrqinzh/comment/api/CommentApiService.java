package com.mrqinzh.comment.api;

public interface CommentApiService {

    void deleteByTypeId(String articleOrCommentId, Long id);
}
