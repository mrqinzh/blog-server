package com.mrqinzh.common.entity;

import com.mrqinzh.framework.mybatis.entity.BaseEntity;

public class Tag extends BaseEntity {

    private String tagName;

    private String tagImg;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTagImg() {
        return tagImg;
    }

    public void setTagImg(String tagImg) {
        this.tagImg = tagImg;
    }
}
