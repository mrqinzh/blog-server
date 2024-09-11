package com.mrqinzh.user.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.user.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesByUserId(Long userId);

}
