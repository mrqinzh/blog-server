package com.mrqinzh.common.domain.entity;

import com.mrqinzh.common.enums.RoleType;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;

import java.util.Date;

public class Role extends BaseEntity {

    private RoleType roleName;

    private Date createTime;
    private Date updateTime;

    private Integer status;

    public RoleType getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleType roleName) {
        this.roleName = roleName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
