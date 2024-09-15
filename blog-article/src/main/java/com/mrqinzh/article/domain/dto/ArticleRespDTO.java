package com.mrqinzh.article.domain.dto;

import com.mrqinzh.framework.common.domain.pojo.dto.RespDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ArticleRespDTO implements RespDTO {

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
