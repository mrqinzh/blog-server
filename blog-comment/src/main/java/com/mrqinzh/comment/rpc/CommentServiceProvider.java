package com.mrqinzh.comment.rpc;

import com.mrqinzh.apis.comment.CommentService;
import com.mrqinzh.common.domain.entity.Comment;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.vo.comment.CommentPageDTO;
import com.mrqinzh.common.domain.vo.comment.CommentVo;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class CommentServiceProvider implements CommentService {

    @Resource
    private com.mrqinzh.comment.service.CommentService commentService;

    @Override
    public List<Comment> list(CommentPageDTO commentPageVo) {
        return commentService.list(commentPageVo);
    }

    @Override
    public List<Comment> getMessageList() {
        return commentService.getMessageList();
    }

    @Override
    public void add(CommentVo commentVo) {
        commentService.add(commentVo);
    }

    @Override
    public Resp getById(String idType, Integer id) {
        return commentService.getById(idType, id);
    }

    @Override
    public Resp deleteById(String idType, Integer id) {
        return commentService.deleteById(idType, id);
    }

    @Override
    public void deleteByTypeId(String articleOrCommentId, Integer id) {
        commentService.deleteByTypeId(articleOrCommentId, id);
    }
}
