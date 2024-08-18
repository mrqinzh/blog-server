package com.mrqinzh.webapp.service;

import com.mrqinzh.common.domain.entity.Comment;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.vo.comment.CommentPageDTO;
import com.mrqinzh.common.domain.vo.comment.CommentVo;

import java.util.List;

public interface CommentService {

    List<Comment> list(CommentPageDTO commentPageVo);

    List<Comment> getMessageList();

    void add(CommentVo commentVo);

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
