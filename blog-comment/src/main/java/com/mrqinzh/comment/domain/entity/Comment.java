package com.mrqinzh.comment.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Comment extends BaseEntity {

    private String avatar;

    private String nickname;

    private String content;

    private String ip;

    /**
     * 类型：
     * 1： 评论    2：留言
     */
    private Integer type;

    private Integer status;

    private Long articleId;
    private Long parentId;

    @TableField(exist = false)
    private List<Comment> comments;

}
