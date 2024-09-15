package com.mrqinzh.article.domain.dto;

import com.mrqinzh.framework.common.domain.pojo.dto.ReqDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagReqDTO implements ReqDTO {

    private Long id;
    private String name;

    private String coverImg;

}
