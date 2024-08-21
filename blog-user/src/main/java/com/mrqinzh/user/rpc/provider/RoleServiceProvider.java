package com.mrqinzh.user.rpc.provider;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.apis.role.RoleService;
import com.mrqinzh.common.domain.entity.Role;
import com.mrqinzh.common.domain.dto.PageDTO;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class RoleServiceProvider implements RoleService {

    @Resource
    private com.mrqinzh.user.service.RoleService roleService;

    @Override
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @Override
    public Page<Role> findPage(PageDTO pageDTO) {
        return roleService.findPage(pageDTO);
    }

    @Override
    public Role getById(Long id) {
        return roleService.getById(id);
    }

    @Override
    public void add(Role role) {
        roleService.add(role);
    }

    @Override
    public void update(Role role) {
        roleService.update(role);
    }

    @Override
    public void delete(Long id) {
        roleService.delete(id);
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleService.getRolesByUserId(userId);
    }
}
