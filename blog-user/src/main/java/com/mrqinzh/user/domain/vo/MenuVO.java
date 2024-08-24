package com.mrqinzh.user.domain.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class MenuVO {

    private Long id;

    @ApiModelProperty(value = "父菜单id")
    private Long parentId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getMenuSort() {
        return menuSort;
    }

    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuPath() {
        return menuPath;
    }

    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentPath() {
        return componentPath;
    }

    public void setComponentPath(String componentPath) {
        this.componentPath = componentPath;
    }

    public Integer getCache() {
        return cache;
    }

    public void setCache(Integer cache) {
        this.cache = cache;
    }

    public Integer getHidden() {
        return hidden;
    }

    public void setHidden(Integer hidden) {
        this.hidden = hidden;
    }
}
