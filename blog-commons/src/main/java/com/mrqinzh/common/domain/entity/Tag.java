package com.mrqinzh.common.domain.entity;

import com.mrqinzh.framework.mybatis.entity.BaseEntity;

public class Tag extends BaseEntity {

    private String name;

    private String coverImg;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }
}
