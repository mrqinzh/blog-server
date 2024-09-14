package com.mrqinzh.comment.domain.enums;

import lombok.Getter;

@Getter
public enum CommentStatus {

    APPLYING("审批中"),
    NORMAL("正常"),
    REJECT("审批拒绝"),
    DELETE("删除")
    ;

    CommentStatus(String desc) {
        this.desc = desc;
    }

    private final String desc;

}
