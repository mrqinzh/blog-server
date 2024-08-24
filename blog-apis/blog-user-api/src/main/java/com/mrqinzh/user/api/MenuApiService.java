package com.mrqinzh.user.api;


import com.mrqinzh.common.domain.entity.Menu;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.vo.menu.MenuVO;

import java.util.List;

public interface MenuApiService {

    List<Menu> findAll();

    List<Menu> findPage(PageDTO pageDTO);

    Menu findById(Integer id);

    void add(MenuVO menuVO);

    void update(MenuVO menuVO);

    void delete(Integer id);

}
