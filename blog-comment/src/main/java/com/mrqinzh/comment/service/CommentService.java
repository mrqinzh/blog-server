package com.mrqinzh.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.comment.domain.dto.ApplyCommentReqDTO;
import com.mrqinzh.comment.domain.dto.CommentReqDTO;
import com.mrqinzh.comment.domain.dto.CommentRespDTO;
import com.mrqinzh.comment.domain.dto.CommentPageReqDTO;

import java.util.List;

public interface CommentService {

    Page<CommentRespDTO> page(CommentPageReqDTO commentPageReqDTO);

    List<CommentRespDTO> list(CommentPageReqDTO commentPageVo);

    List<CommentRespDTO> getMessageList();

    void add(CommentReqDTO commentReqVo);

    List<CommentRespDTO> getById(String idType, Long id);

    /**
     * 根据id 删除评论
     * @param idType userId、articleId、commentId 分别对应不同的id类型
     * @param id id 值
     * @return
     */
    void deleteById(String idType, Long id);

    void applyComments(ApplyCommentReqDTO applyCommentReqDTO);
}
