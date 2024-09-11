package com.mrqinzh.framework.common.domain.dto;

import com.mrqinzh.framework.common.domain.pojo.DTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "分页信息类")
@Data
public class PageDTO implements DTO {

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    /**
     * 条件属性
     */
    private String condition;

    /**
     * 排序
     */
    private String orderBy;
}
