package com.mrqinzh.article.domain.vo;

import com.mrqinzh.framework.common.domain.pojo.vo.ReqVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TagReqVO implements ReqVO {

    private Long id;
    private String name;

    private String coverImg;

}
