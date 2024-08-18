package com.mrqinzh.common.domain.vo.comment;

import com.mrqinzh.common.domain.vo.BaseVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(description = "添加评论请求实体")
public class CommentVo implements BaseVO {

    @ApiModelProperty(value = "昵称")
    @NotBlank
    private String nickname;

    @ApiModelProperty(value = "评论内容")
    @NotBlank
    private String commentContent;

    @ApiModelProperty(value = "类型---1：评论  2：留言")
    @NotNull
    private Integer type;

    private Long parentId;
    private Long articleId;
    private String commentIp;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getCommentIp() {
        return commentIp;
    }

    public void setCommentIp(String commentIp) {
        this.commentIp = commentIp;
    }
}
