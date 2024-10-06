package com.mrqinzh.comment.domain.dto;

import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.framework.common.domain.dto.ReqDTO;
import com.mrqinzh.framework.common.domain.page.PageRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CommentPageReqDTO extends PageRequest implements ReqDTO {

    private String nickname;

    private String ip;

    private Date startTime;

    private Date endTime;

    private BusinessType type;

}
