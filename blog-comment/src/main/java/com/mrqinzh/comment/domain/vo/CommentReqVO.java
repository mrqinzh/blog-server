package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.framework.common.domain.pojo.VO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "添加评论请求实体")
public class CommentReqVO implements VO {

    @Schema(description = "昵称")
    @NotBlank
    private String nickname;

    @Schema(description = "评论内容")
    @NotBlank
    private String commentContent;

    @Schema(description = "类型---1：评论  2：留言")
    @NotNull
    private Integer type;

    private Long parentId;
    private Long articleId;
    private String commentIp;

}
