package com.mrqinzh.article.domain.vo;

import com.mrqinzh.article.domain.entity.Article;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "添加/修改文章请求实体")
public class ArticleVO {

    @Schema(description = "文章id")
    private Long id;

    @Schema(description = "文章标题")
    @NotBlank
    private String articleTitle;

    @Schema(description = "文章摘要")
    private String articleSummary;

    @Schema(description = "文章封面图")
    private String articleCoverImg;

    @Schema(description = "文章markdown内容")
    @NotBlank
    private String articleContentMd;

    @Schema(description = "文章标签")
    @NotBlank
    private String articleTag;

    @Schema(description = "文章类型： 原创 --- 转载")
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
