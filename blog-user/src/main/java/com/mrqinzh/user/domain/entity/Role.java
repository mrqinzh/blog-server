package com.mrqinzh.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import com.mrqinzh.user.domain.enums.RoleType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName("sys_role")
public class Role extends BaseEntity {

    private RoleType roleName;
    private Integer status;

}
