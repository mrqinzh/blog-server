package com.mrqinzh.comment.domain.dto;

import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.framework.common.domain.pojo.dto.ReqDTO;
import lombok.Data;

import java.util.Objects;

@Data
public class CommentReqDTO implements ReqDTO {

    private String nickname;

    private String commentContent;

    private BusinessType type;

    private Long parentId;
    private Long articleId;
    private String commentIp;

    public boolean isReply() {
        return Objects.nonNull(parentId);
    }

}
