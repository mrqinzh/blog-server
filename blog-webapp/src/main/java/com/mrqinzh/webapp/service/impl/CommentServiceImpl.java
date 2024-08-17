package com.mrqinzh.webapp.service.impl;

import com.mrqinzh.common.domain.entity.Comment;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.vo.comment.CommentPageDTO;
import com.mrqinzh.common.domain.vo.comment.CommentVo;
import com.mrqinzh.webapp.client.CommentClient;
import com.mrqinzh.webapp.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentClient commentClient;

    @Override
    public List<Comment> list(CommentPageDTO commentPageVo) {
        return commentClient.list(commentPageVo);
    }

    @Override
    public List<Comment> getMessageList() {
        return commentClient.getMessageList();
    }

    @Override
    public void add(CommentVo commentVo) {
        commentClient.add(commentVo);
    }

    @Override
    public Resp getById(String idType, Integer id) {
        return commentClient.getById(idType, id);
    }

    @Override
    public Resp deleteById(String idType, Integer id) {
        return commentClient.deleteById(idType, id);
    }

    @Override
    public void deleteByTypeId(String articleOrCommentId, Integer id) {
        commentClient.deleteByTypeId(articleOrCommentId, id);
    }
}
