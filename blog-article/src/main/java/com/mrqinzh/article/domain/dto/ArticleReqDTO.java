package com.mrqinzh.article.domain.dto;

import com.mrqinzh.framework.common.domain.pojo.dto.ReqDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticleReqDTO implements ReqDTO {

    private Long id;

    private String articleTitle;

    private String articleSummary;

    private String articleCoverImg;

    private String articleContentMd;

    private String articleTag;

    private String articleType;

}
