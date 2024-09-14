package com.mrqinzh.comment.domain.dto;

import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.framework.common.domain.pojo.dto.ReqDTO;
import com.mrqinzh.framework.common.domain.pojo.page.BasePageReq;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CommentPageReqDTO extends BasePageReq implements ReqDTO {

    private String nickname;

    private String ip;

    private Date startTime;

    private Date endTime;

    private BusinessType type;

}
