package com.mrqinzh.apis.role;

import com.mrqinzh.common.domain.entity.Role;
import com.mrqinzh.common.domain.dto.PageDTO;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    List<Role> findPage(PageDTO pageDTO);

    Role getById(Integer id);

    void add(Role role);

    void update(Role role);

    void delete(Integer id);

    List<Role> getRolesByUserId(Integer userId);

}
