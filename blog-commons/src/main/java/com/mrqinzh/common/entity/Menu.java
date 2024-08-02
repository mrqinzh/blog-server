package com.mrqinzh.common.entity;

import com.mrqinzh.commons.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
public class Menu extends BaseEntity {

    private Integer parentId;

    /**
     * 菜单名
     */
    private String menuTitle;

    /**
     * 组件name
     */
    private String componentName;

    /**
     * 组件位置
     */
    private String componentPath;

    private String redirect;

    private String icon;

    private String menuPath;

    private Integer cache;

    private Integer hidden;

    private Integer menuSort;

    private Integer type;

    private String permission;

    private Integer status;


    private List<Menu> menuChildren;

}
