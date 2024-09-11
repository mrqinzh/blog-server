package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.user.domain.entity.Role;
import com.mrqinzh.user.dal.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(Role::getStatus, 0);
        List<Role> roles = roleMapper.selectList(queryWrapper);
        return roles;
    }

    @Override
    public Page<Role> findPage(PageDTO pageDTO) {
        Page<Role> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize());
        return roleMapper.selectPage(page, new LambdaQueryWrapper<Role>().eq(Role::getStatus, 0));
    }

    @Override
    public Role getById(Long id) {
        Role role = roleMapper.selectById(id);
        return role;
    }

    @Override
    public void add(Role role) {
        if (role.getRoleName() == null) {
            throw new BizException(ErrorCode.BAD_PARAMETER, "请输入角色名称");
        }
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setStatus(0);
        roleMapper.insert(role);
    }

    @Override
    public void update(Role role) {
        if (role.getId() == null || role.getRoleName() == null) {
            throw new BizException(ErrorCode.BAD_PARAMETER, "请输入角色名称");
        }
        role.setUpdateTime(new Date());
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
