package com.mrqinzh.webapp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Page<Role> findPage(PageDTO pageDTO);

    Role getById(Long id);

    void add(Role role);

    void update(Role role);

    void delete(Long id);

    List<Role> getRolesByUserId(Long userId);

}
