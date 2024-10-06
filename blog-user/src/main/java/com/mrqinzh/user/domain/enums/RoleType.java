package com.mrqinzh.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    SUPER_ADMIN("超级管理员"),
    ADMIN("管理员"),
    USER("用户"),
    VISITOR("访客")
    ;

    private final String desc;


}
