package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.framework.common.domain.pojo.vo.RespVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentRespVO implements RespVO {

    private String nickname;

    private String commentContent;

    private BusinessType type;

    private Long parentId;
    private Long articleId;
    private String commentIp;

    private List<CommentRespVO> comments;

}
