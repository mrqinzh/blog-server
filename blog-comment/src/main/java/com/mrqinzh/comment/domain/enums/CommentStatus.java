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

    public static CommentStatus getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        switch (code) {
            case 0 -> {
                return APPLYING;
            }
            case 1 -> {
                return NORMAL;
            }
            case 2 -> {
                return REJECT;
            }
            case 3 -> {
                return DELETE;
            }
            default -> {
                return null;
            }
        }
    }

}
