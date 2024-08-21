package com.mrqinzh.webapp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.entity.Role;
import com.mrqinzh.webapp.client.RoleClient;
import com.mrqinzh.webapp.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleClient roleClient;

    @Override
    public List<Role> findAll() {
        return roleClient.findAll();
    }

    @Override
    public Page<Role> findPage(PageDTO pageDTO) {
        return roleClient.findPage(pageDTO);
    }

    @Override
    public Role getById(Long id) {
        return roleClient.getById(id);
    }

    @Override
    public void add(Role role) {
        roleClient.add(role);
    }

    @Override
    public void update(Role role) {
        roleClient.update(role);
    }

    @Override
    public void delete(Long id) {
        roleClient.delete(id);
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        return roleClient.getRolesByUserId(userId);
    }
}
