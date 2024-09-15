package com.mrqinzh.article.domain.dto;

import com.mrqinzh.framework.common.domain.dto.RespDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TagRespDTO implements RespDTO {

    private Long id;
    private String name;

    private String coverImg;

    private Date createTime;
    private Date updateTime;

}
