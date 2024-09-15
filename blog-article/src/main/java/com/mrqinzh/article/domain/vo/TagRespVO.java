package com.mrqinzh.article.domain.vo;

import com.mrqinzh.framework.common.domain.pojo.vo.RespVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TagRespVO implements RespVO {

    private Long id;
    private String name;

    private String coverImg;

    private Date createTime;
    private Date updateTime;

}
