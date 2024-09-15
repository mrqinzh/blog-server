package com.mrqinzh.user.dal.repo;

import com.mrqinzh.user.dal.mapper.RoleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class RoleRepository {

    @Resource
    private RoleMapper roleMapper;

}
