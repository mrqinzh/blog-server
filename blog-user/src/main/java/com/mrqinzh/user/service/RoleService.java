package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.domain.page.PageCondition;
import com.mrqinzh.user.domain.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Page<Role> findPage(PageCondition pageReq);

    Role getById(Long id);

    void add(Role role);

    void update(Role role);

    void delete(Long id);

    List<Role> getRolesByUserId(Long userId);

}
