package com.mrqinzh.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.common.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    List<User> selectAll();

    Boolean add(User user);

    List<User> list(); // 查询所有用户

    User getByUsernameOrEmail(String usernameOrEmail); // 登录方法
}
