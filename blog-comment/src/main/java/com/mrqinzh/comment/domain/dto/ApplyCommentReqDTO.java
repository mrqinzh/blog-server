package com.mrqinzh.comment.domain.dto;

import com.mrqinzh.framework.common.domain.pojo.dto.ReqDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApplyCommentReqDTO implements ReqDTO {

    private List<Long> commentIds;

    private boolean approve;

    public ApplyCommentReqDTO(List<Long> commentIds, boolean approve) {
        this.commentIds = commentIds;
        this.approve = approve;
    }
}
