package com.mrqinzh.comment.dal.repo;

import com.mrqinzh.comment.dal.mapper.CommentMapper;
import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.vo.CommentPageDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRepository {

    @Resource
    private CommentMapper commentMapper;

    public void insert(Comment comment) {
        commentMapper.insert(comment);
    }

    public List<Comment> queryByTypeId(String idType, Long id) {
        return commentMapper.getById(idType, id);
    }

    public List<Comment> list(CommentPageDTO commentPageVo) {
        return commentMapper.list(commentPageVo);
    }

    public void deleteByTypeId(String idType, Long id) {
        commentMapper.deleteByTypeId(idType, id);
    }
}
