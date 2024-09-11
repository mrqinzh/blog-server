package com.mrqinzh.comment.service;

import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.vo.CommentPageDTO;
import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.framework.common.resp.Resp;

import java.util.List;

public interface CommentService {

    List<Comment> list(CommentPageDTO commentPageVo);

    List<Comment> getMessageList();

    void add(CommentReqVO commentReqVo);

    Resp getById(String idType, Long id);

    /**
     * 根据id 删除评论
     * @param idType userId、articleId、commentId 分别对应不同的id类型
     * @param id id 值
     * @return
     */
    Resp deleteById(String idType, Long id);

    void deleteByTypeId(String articleOrCommentId, Long id);
}
