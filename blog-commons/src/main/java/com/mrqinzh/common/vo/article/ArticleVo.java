package com.mrqinzh.common.vo.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "添加/修改文章请求实体")
public class ArticleVo {

    @ApiModelProperty(value = "文章id")
    private Integer id;

    @ApiModelProperty(value = "文章标题")
    @NotBlank
    private String articleTitle;

    @ApiModelProperty(value = "文章摘要")
    private String articleSummary;

    @ApiModelProperty(value = "文章封面图")
    private String articleCoverImg;

    @ApiModelProperty(value = "文章markdown内容")
    @NotBlank
    private String articleContentMd;

    @ApiModelProperty(value = "文章标签")
    @NotBlank
    private String articleTag;

    @ApiModelProperty(value = "文章类型： 原创 --- 转载")
    @NotBlank
    private String articleType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleCoverImg() {
        return articleCoverImg;
    }

    public void setArticleCoverImg(String articleCoverImg) {
        this.articleCoverImg = articleCoverImg;
    }

    public String getArticleContentMd() {
        return articleContentMd;
    }

    public void setArticleContentMd(String articleContentMd) {
        this.articleContentMd = articleContentMd;
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }
}
