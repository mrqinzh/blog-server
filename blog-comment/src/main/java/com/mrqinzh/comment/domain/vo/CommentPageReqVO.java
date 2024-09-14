package com.mrqinzh.comment.domain.vo;

import com.mrqinzh.framework.common.domain.pojo.page.BasePageReq;
import com.mrqinzh.framework.common.domain.pojo.vo.ReqVO;
import lombok.Data;

@Data
public class CommentPageReqVO extends BasePageReq implements ReqVO {

    /**
     * 评论昵称
     */
    private String nickname;

}
