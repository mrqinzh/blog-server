package com.mrqinzh.comment.rpc;

import com.mrqinzh.comment.api.CommentApi;
import com.mrqinzh.comment.service.CommentService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CommentServiceProvider implements CommentApi {

    @Resource
    private CommentService commentService;

    @Override
    public void deleteByTypeId(String articleOrCommentId, Long id) {
        commentService.deleteByTypeId(articleOrCommentId, id);
    }
}
