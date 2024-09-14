package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.framework.common.domain.pojo.vo.ReqVO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Schema(description = "添加评论请求实体")
public class CommentReqVO implements ReqVO {

    @Schema(description = "昵称")
    @NotBlank
    private String nickname;

    @Schema(description = "评论内容")
    @NotBlank
    private String commentContent;

    @Schema(description = "类型---1：评论  2：留言")
    @NotNull
    private BusinessType type;

    private Long parentId;
    private Long articleId;
    private String commentIp;

    public void setType(Integer code) {
        this.type = BusinessType.getByCode(code);
    }

}
