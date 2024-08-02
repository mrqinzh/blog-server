package com.mrqinzh.common.vo.article;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@ApiModel(description = "添加/修改文章请求实体")
@Data
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

}
