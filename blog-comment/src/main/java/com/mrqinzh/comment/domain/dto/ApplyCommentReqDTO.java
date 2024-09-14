package com.mrqinzh.comment.domain.dto;

import com.mrqinzh.framework.common.domain.pojo.dto.ReqDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApplyCommentReqDTO implements ReqDTO {

    private List<Long> commentIds;

    private boolean approve;

    public ApplyCommentReqDTO(List<Long> commentIds, boolean approve) {
        this.commentIds = commentIds;
        this.approve = approve;
    }
}
