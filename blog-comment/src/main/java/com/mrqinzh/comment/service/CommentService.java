package com.mrqinzh.comment.service;

import com.mrqinzh.comment.domain.dto.CommentReqDTO;
import com.mrqinzh.comment.domain.dto.CommentRespDTO;
import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.vo.CommentPageDTO;
import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.framework.common.resp.Resp;

import java.util.List;

public interface CommentService {

    List<CommentRespDTO> list(CommentPageDTO commentPageVo);

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

}
