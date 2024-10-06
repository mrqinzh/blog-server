package com.mrqinzh.user.dal.repo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.domain.page.PageCondition;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import com.mrqinzh.user.dal.mapper.UserMapper;
import com.mrqinzh.user.domain.bo.UserBO;
import com.mrqinzh.user.domain.convert.UserConvert;
import com.mrqinzh.user.domain.entity.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepository {

    @Resource
    private UserMapper userMapper;

    public Page<UserBO> page(PageCondition pageCondition) {
        Page<User> page = new Page<>(pageCondition.getCurrentPage(), pageCondition.getPageSize());
        page = userMapper.selectPage(page, null);

        return PageUtils.convert(page, UserConvert.INSTANCE::convert2BO);
    }

    public UserBO queryById(Long id) {
        User user = userMapper.selectById(id);
        return UserConvert.INSTANCE.convert2BO(user);
    }

    public UserBO queryByUsername(String username) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        return UserConvert.INSTANCE.convert2BO(user);
    }

    public long selectCount() {
        return Optional.ofNullable(userMapper.selectCount(null)).orElse(0L);
    }
}
