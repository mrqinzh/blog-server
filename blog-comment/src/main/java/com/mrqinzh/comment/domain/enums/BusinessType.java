package com.mrqinzh.comment.domain.enums;

public enum BusinessType {

    COMMENT, // 评论
    MESSAGE // 留言
    ;

    public static BusinessType getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        switch (code) {
            case 1 -> {
                return COMMENT;
            }
            case 2 -> {
                return MESSAGE;
            }
            default -> {
                return null;
            }
        }
    }
}
