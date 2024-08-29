package com.mrqinzh.article.domain.entity;

import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Tag extends BaseEntity {

    private String name;

    private String coverImg;

}
