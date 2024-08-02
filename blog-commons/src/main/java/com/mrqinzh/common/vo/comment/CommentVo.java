package com.mrqinzh.common.vo.comment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "添加评论请求实体")
@Data
public class CommentVo {

    @ApiModelProperty(value = "昵称")
    @NotBlank
    private String nickname;

    @ApiModelProperty(value = "评论内容")
    @NotBlank
    private String commentContent;

    @ApiModelProperty(value = "类型---1：评论  2：留言")
    @NotNull
    private Integer type;

    private Integer parentId;
    private Integer articleId;

}
