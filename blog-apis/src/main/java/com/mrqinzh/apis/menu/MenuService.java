package com.mrqinzh.apis.menu;


import com.mrqinzh.common.entity.Menu;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.menu.MenuVO;

import java.util.List;

public interface MenuService {

    List<Menu> findAll();

    List<Menu> findPage(PageVO pageVO);

    Menu findById(Integer id);

    void add(MenuVO menuVO);

    void update(MenuVO menuVO);

    void delete(Integer id);

}
