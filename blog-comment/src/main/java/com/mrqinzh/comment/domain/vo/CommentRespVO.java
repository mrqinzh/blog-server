package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.framework.common.domain.pojo.vo.VO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentRespVO implements VO {

    private String nickname;

    private String commentContent;

    private Integer type;

    private Long parentId;
    private Long articleId;
    private String commentIp;

    private List<CommentRespVO> comments;

}
