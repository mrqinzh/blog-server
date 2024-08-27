package com.mrqinzh.user.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuVO {

    private Long id;

    @Schema(description = "父菜单id")
    private Long parentId;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "排序等级")
    private Integer menuSort;

    @Schema(description = "菜单名")
    @NotBlank
    private String menuTitle;

    @Schema(description = "菜单路径")
    @NotBlank
    private String menuPath;

    @Schema(description = "组件名")
    private String componentName;

    @Schema(description = "组件路径")
    @NotBlank
    private String componentPath;

    @Schema(description = "是否缓存，默认0，缓存")
    private Integer cache;

    @Schema(description = "是否隐藏，默认1，不隐藏")
    private Integer hidden;

}
