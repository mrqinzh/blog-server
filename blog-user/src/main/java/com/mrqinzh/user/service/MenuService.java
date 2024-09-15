package com.mrqinzh.user.service;

import com.mrqinzh.framework.common.domain.pojo.page.PageCondition;
import com.mrqinzh.user.domain.entity.Menu;
import com.mrqinzh.user.domain.vo.MenuVO;

import java.util.List;

public interface MenuService {

    List<Menu> findAll();

    List<Menu> findPage(PageCondition pageReq);

    Menu findById(Integer id);

    void add(MenuVO menuVO);

    void update(MenuVO menuVO);

    void delete(Integer id);

}
