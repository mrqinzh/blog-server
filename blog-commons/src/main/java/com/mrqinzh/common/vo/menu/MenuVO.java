package com.mrqinzh.common.vo.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MenuVO {

    private Integer id;

    @ApiModelProperty(value = "父菜单id")
    private Integer parentId;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "排序等级")
    private Integer menuSort;

    @ApiModelProperty(value = "菜单名")
    @NotBlank
    private String menuTitle;

    @ApiModelProperty(value = "菜单路径")
    @NotBlank
    private String menuPath;

    @ApiModelProperty(value = "组件名")
    private String componentName;

    @ApiModelProperty(value = "组件路径")
    @NotBlank
    private String componentPath;

    @ApiModelProperty(value = "是否缓存，默认0，缓存")
    private Integer cache;

    @ApiModelProperty(value = "是否隐藏，默认1，不隐藏")
    private Integer hidden;

}
