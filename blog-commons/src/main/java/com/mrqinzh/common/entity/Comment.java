package com.mrqinzh.common.entity;

import com.mrqinzh.commons.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comment extends BaseEntity {

    private String avatar;

    private String nickname;

    private String commentContent;

    private Date commentTime;

    private String ip;

    /**
     * 类型：
     * 1： 评论    2：留言
     */
    private Integer type;

    private Integer status;

    private Integer articleId;
    private Article article;

    private Integer parentId;

    private List<Comment> comments;

}
