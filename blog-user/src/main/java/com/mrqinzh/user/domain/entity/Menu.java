package com.mrqinzh.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@TableName("sys_menu")
public class Menu extends BaseEntity {

    private Long parentId;

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

    @TableField(exist = false)
    private List<Menu> menuChildren;

}
