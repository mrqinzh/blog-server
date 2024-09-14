package com.mrqinzh.comment.rpc;

import com.mrqinzh.comment.api.CommentApi;
import com.mrqinzh.comment.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentServiceProvider implements CommentApi {

    @Resource
    private CommentService commentService;

    @Override
    public void deleteByTypeId(String articleOrCommentId, Long id) {
        commentService.deleteById(articleOrCommentId, id);
    }
}
