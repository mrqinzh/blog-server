package com.mrqinzh.comment.dal.repo;

import com.mrqinzh.comment.dal.mapper.CommentMapper;
import com.mrqinzh.comment.domain.entity.Comment;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class CommentRepository {

    @Resource
    private CommentMapper commentMapper;

    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
