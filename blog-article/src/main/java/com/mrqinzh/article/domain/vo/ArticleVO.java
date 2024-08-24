package com.mrqinzh.article.domain.vo;


import javax.validation.constraints.NotBlank;

@ApiModel(description = "添加/修改文章请求实体")
public class ArticleVO {

    @ApiModelProperty(value = "文章id")
    private Long id;

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

    public ArticleVO() {

    }

    public ArticleVO(Article article) {
        this.id = article.getId();
        this.articleTitle = article.getTitle();
        this.articleSummary = article.getSummary();
        this.articleCoverImg = article.getCoverImg();
        this.articleContentMd = article.getContentMd();
        this.articleTag = article.getTag();
        this.articleType = article.getType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
