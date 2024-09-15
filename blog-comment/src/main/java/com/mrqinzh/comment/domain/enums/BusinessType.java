package com.mrqinzh.comment.domain.enums;

import lombok.Getter;

@Getter
public enum BusinessType {

    COMMENT("评论"), // 评论
    MESSAGE("留言") // 留言
    ;

    BusinessType(String desc) {
        this.desc = desc;
    }

    private final String desc;

    public static Integer getCode(BusinessType businessType) {
        if (businessType == null) {
            return null;
        }
        return businessType.ordinal();
    }

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
