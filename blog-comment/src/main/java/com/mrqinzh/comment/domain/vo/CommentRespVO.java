package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.comment.domain.enums.CommentStatus;
import com.mrqinzh.framework.common.domain.pojo.vo.RespVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CommentRespVO implements RespVO {

    private Long id;
    private String avatar;
    private String nickname;

    private String content;

    private BusinessType type;
    private CommentStatus status;

    private Long parentId;
    private Long articleId;
    private String ip;
    private Date createTime;

    private List<CommentRespVO> comments;

}
