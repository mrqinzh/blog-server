package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.framework.common.domain.page.PageRequest;
import com.mrqinzh.framework.common.domain.vo.ReqVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentPageReqVO extends PageRequest implements ReqVO {

    /**
     * 评论昵称
     */
    private String nickname;

    private String ip;

    private Date startTime;
    private Date endTime;


}
