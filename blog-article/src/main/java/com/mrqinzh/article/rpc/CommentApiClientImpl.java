package com.mrqinzh.article.rpc;

import com.mrqinzh.comment.api.CommentApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class CommentApiClientImpl implements CommentApiClient {

    @Resource
    private CommentApi commentApiService;

    @Override
    public void deleteByTypeId(String articleOrCommentId, Long id) {
        commentApiService.deleteByTypeId(articleOrCommentId, id);
    }
}
