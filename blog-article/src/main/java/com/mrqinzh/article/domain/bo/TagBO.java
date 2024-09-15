package com.mrqinzh.article.domain.bo;

import com.mrqinzh.framework.common.domain.bo.BO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TagBO implements BO {

    private Long id;
    private String name;

    private String coverImg;

    private Date createTime;
    private Date updateTime;

}
