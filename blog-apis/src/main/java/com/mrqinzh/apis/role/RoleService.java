package com.mrqinzh.apis.role;

import com.mrqinzh.common.entity.Role;
import com.mrqinzh.common.vo.PageVO;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    List<Role> findPage(PageVO pageVO);

    Role getById(Integer id);

    void add(Role role);

    void update(Role role);

    void delete(Integer id);

    List<Role> getRolesByUserId(Integer userId);

}
