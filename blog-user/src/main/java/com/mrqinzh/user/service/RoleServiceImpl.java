package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.domain.page.PageRequest;
import com.mrqinzh.user.domain.entity.Role;
import com.mrqinzh.user.dal.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Role::getStatus, 0);
        List<Role> roles = roleMapper.selectList(queryWrapper);
        return roles;
    }

    @Override
    public Page<Role> findPage(PageRequest pageReq) {
        Page<Role> page = new Page<>(pageReq.getCurrentPage(), pageReq.getPageSize());
        return roleMapper.selectPage(page, new LambdaQueryWrapper<Role>().eq(Role::getStatus, 0));
    }

    @Override
    public Role getById(Long id) {
        Role role = roleMapper.selectById(id);
        return role;
    }

    @Override
    public void add(Role role) {
        role.setStatus(0);
        roleMapper.insert(role);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateById(role);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteById(id);
    }

    public List<Role> getRolesByUserId(Long userId) {
        return roleMapper.getRolesByUserId(userId);
    }

}
