package com.mrqinzh.article.domain.entity;

import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Article extends BaseEntity {

    private String author;
    private String title;
    private String summary;

    private String coverImg;
    private String contentMd;

    private String tag;

    private String type;

    private Integer views;

    /**
     * 状态值
     */
    private Integer status;

    private Long userId;

}
