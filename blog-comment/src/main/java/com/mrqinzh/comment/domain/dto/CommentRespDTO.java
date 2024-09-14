package com.mrqinzh.comment.domain.dto;

import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.comment.domain.enums.CommentStatus;
import com.mrqinzh.framework.common.domain.pojo.dto.RespDTO;
import lombok.Data;

import java.util.List;

@Data
public class CommentRespDTO implements RespDTO {

    private Long id;
    private String avatar;

    private String nickname;

    private String content;

    private String ip;

    private BusinessType type;

    private CommentStatus status;

    private Long articleId;
    private Long parentId;

    private List<CommentRespDTO> comments;

}
