package com.mrqinzh.comment.service;

import com.mrqinzh.common.entity.Comment;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.comment.CommentPageVo;
import com.mrqinzh.common.vo.comment.CommentVo;

import java.util.List;

public interface CommentService {

    List<Comment> list(CommentPageVo commentPageVo);

    List<Comment> getMessageList();

    void add(CommentVo commentVo);

    Resp getById(String idType, Integer id);

    /**
     * 根据id 删除评论
     * @param idType userId、articleId、commentId 分别对应不同的id类型
     * @param id id 值
     * @return
     */
    Resp deleteById(String idType, Integer id);

    void deleteByTypeId(String articleOrCommentId, Integer id);
}
