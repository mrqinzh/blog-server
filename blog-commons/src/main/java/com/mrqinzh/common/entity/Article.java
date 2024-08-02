package com.mrqinzh.common.entity;

import com.mrqinzh.commons.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true) // 开启链式编程
public class Article extends BaseEntity {

    private String articleAuthor;
    private String articleTitle;
    private String articleSummary;

    private String articleCoverImg;

    private Date articleCreateTime;
    private Date articleUpdateTime;

    private String articleContentMd;

    private String articleTag;

    private String articleType;

    private Integer articleViews;

    /**
     * 状态值
     */
    private Integer status;

    private Integer userId;
    private User user;
}
