package com.mrqinzh.framework.common.domain.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Schema(description = "分页信息基类")
public class PageCondition implements Serializable {

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    /**
     * 条件属性
     */
    @Deprecated
    private String condition;

    /**
     * 排序
     */
    private String orderBy;

}
