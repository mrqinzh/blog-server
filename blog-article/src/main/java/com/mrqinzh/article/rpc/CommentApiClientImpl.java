package com.mrqinzh.article.rpc;

import com.mrqinzh.comment.api.CommentApiService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class CommentApiClientImpl implements CommentApiClient {

    @DubboReference
    private CommentApiService commentApiService;

    @Override
    public void deleteByTypeId(String articleOrCommentId, Long id) {
        commentApiService.deleteByTypeId(articleOrCommentId, id);
    }
}
