package com.mrqinzh.article.domain.bo;

import com.mrqinzh.framework.common.domain.pojo.BO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ArticleBO implements BO {

    private Long id;
    private String author;
    private String title;
    private String summary;

    private String coverImg;
    private String contentMd;

    private String tag;

    private String type;

    private Integer views;

    private Integer status;

    private Date createTime;
    private Date updateTime;

    private Long userId;

}
