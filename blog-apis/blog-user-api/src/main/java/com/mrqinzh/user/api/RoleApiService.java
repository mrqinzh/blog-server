package com.mrqinzh.user.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.common.domain.entity.Role;
import com.mrqinzh.common.domain.dto.PageDTO;

import java.util.List;

public interface RoleApiService {

    List<Role> findAll();

    Page<Role> findPage(PageDTO pageDTO);

    Role getById(Long id);

    void add(Role role);

    void update(Role role);

    void delete(Long id);

    List<Role> getRolesByUserId(Long userId);

}
