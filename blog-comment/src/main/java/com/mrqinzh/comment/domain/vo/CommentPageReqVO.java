package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.framework.common.domain.pojo.page.BasePageReq;
import com.mrqinzh.framework.common.domain.pojo.vo.ReqVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentPageReqVO extends BasePageReq implements ReqVO {

    /**
     * 评论昵称
     */
    private String nickname;

    private String ip;

    private Date startTime;
    private Date endTime;


}
