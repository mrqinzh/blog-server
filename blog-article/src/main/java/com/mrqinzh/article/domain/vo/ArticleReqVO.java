package com.mrqinzh.article.domain.vo;

import com.mrqinzh.article.domain.entity.Article;
import com.mrqinzh.framework.common.domain.pojo.vo.ReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "添加/修改文章请求实体")
@Getter
@Setter
@NoArgsConstructor
public class ArticleReqVO implements ReqVO {

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

    public ArticleReqVO(Article article) {
        this.id = article.getId();
        this.articleTitle = article.getTitle();
        this.articleSummary = article.getSummary();
        this.articleCoverImg = article.getCoverImg();
        this.articleContentMd = article.getContentMd();
        this.articleTag = article.getTag();
        this.articleType = article.getType();
    }

}
