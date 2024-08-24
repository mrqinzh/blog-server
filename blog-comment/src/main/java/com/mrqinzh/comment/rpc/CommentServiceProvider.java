package com.mrqinzh.comment.rpc;

import com.mrqinzh.comment.api.CommentApiService;
import com.mrqinzh.comment.service.CommentService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class CommentServiceProvider implements CommentApiService {

    @Resource
    private CommentService commentService;

    @Override
    public void deleteByTypeId(String articleOrCommentId, Long id) {
        commentService.deleteByTypeId(articleOrCommentId, id);
    }
}
