package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.framework.common.domain.vo.ReqVO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ApplyCommentReqVO implements ReqVO {

    private List<Long> commentIds;

    @NotNull(message = "approve can not be null")
    private Integer approve;

    public boolean isApprove() {
        return approve == 1;
    }


}
