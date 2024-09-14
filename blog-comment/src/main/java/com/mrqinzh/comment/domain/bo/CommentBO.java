package com.mrqinzh.comment.domain.bo;

import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.comment.domain.enums.CommentStatus;
import com.mrqinzh.framework.common.domain.pojo.BO;
import lombok.Data;

@Data
public class CommentBO implements BO {

    private Long id;
    private String avatar;

    private String nickname;

    private String commentContent;

    private String ip;

    private BusinessType type;

    private CommentStatus status;

    private Long articleId;
    private Long parentId;

}
