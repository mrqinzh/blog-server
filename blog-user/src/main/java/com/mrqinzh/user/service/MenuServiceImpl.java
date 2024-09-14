package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mrqinzh.framework.common.domain.pojo.page.BasePageReq;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.user.domain.entity.Menu;
import com.mrqinzh.user.domain.vo.MenuVO;
import com.mrqinzh.user.dal.mapper.MenuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> findAll() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Menu::getStatus, 0).orderByAsc(Menu::getMenuSort);
        List<Menu> menus = menuMapper.selectList(queryWrapper);
        if (menus == null) return null;

        // 将查询出来的菜单进行处理，将子菜单们添加到对应的父菜单下
        List<Menu> rootMenu = new ArrayList<>();
        for (Menu menu : menus) {
            if (menu.getParentId() == 0) {
                rootMenu.add(menu);
            }
        }
        for (Menu entry : rootMenu) {
            List<Menu> childList = getChildren(entry.getId(), menus);
            entry.setMenuChildren(childList);
        }

        return rootMenu;
    }

    @Override
    public List<Menu> findPage(BasePageReq pageReq) {
        List<Menu> menus = menuMapper.selectList(new QueryWrapper<Menu>().lambda().eq(Menu::getStatus, 0));
        return menus;
    }

    @Override
    public Menu findById(Integer id) {
        return menuMapper.selectById(id);
    }

    @Override
    public void add(MenuVO menuVO) {
        Menu parentMenu = null;
        // 判断添加的是否为子菜单
        if (menuVO.getParentId() != null && menuVO.getParentId() != 0) {
            parentMenu = menuMapper.selectById(menuVO.getParentId());
            if (parentMenu == null) {
                throw new BizException(ErrorCode.BAD_PARAMETER, "上级菜单选择错误");
            }
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVO, menu);
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        menu.setStatus(0);

        menuMapper.insert(menu);
    }

    @Override
    public void update(MenuVO menuVO) {
        if (menuVO.getId() == null || menuVO.getId() == 0) {
            throw new BizException(ErrorCode.BAD_PARAMETER, "参数校验错误，缺少菜单id");
        }
        Menu menu = menuMapper.selectById(menuVO.getId());
        BeanUtils.copyProperties(menuVO, menu);

        menu.setUpdateTime(new Date());

        menuMapper.updateById(menu);
    }

    @Override
    public void delete(Integer id) {
        menuMapper.deleteById(id);
    }


    public List<Menu> getChildren(Long id, List<Menu> allMenu) {
        List<Menu> childList = new ArrayList<>();
        for (Menu entry : allMenu) {
            if (entry.getParentId() == id) {
                childList.add(entry);
            }
        }
        // 递归
        for (Menu entry : childList) {
            entry.setMenuChildren(getChildren(entry.getId(), allMenu));
        }
        if (childList.size() == 0) {
            return new ArrayList<>();
        }
        return childList;
    }

}
