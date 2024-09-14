package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrqinzh.framework.common.domain.pojo.dto.PageDTO;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.redis.utils.RedisUtil;
import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.vo.UserVO;
import com.mrqinzh.user.dal.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> test() {
        return userMapper.selectAll();
    }

    @Override
    public Resp update(UserVO userVO) {

        User securityUser = RedisUtil.get("user"); // 当前登录的用户

        User user = new User();

        if (userVO.getUserPwd() != null && userVO.getNewPass() != null) {
            // 修改密码操作
            if (!userVO.getUserPwd().equals(securityUser.getPassword())) {
                throw new BizException(ErrorCode.BAD_PARAMETER, "原密码发生了错误。。。");
            }
            // Todo 此处可以对密码进行加密。。。
            user.setPwd(userVO.getNewPass()); // 设置新密码
        } else {
            // 避免前端传入密码脏数据，导致BeanUtils.copyProperties 复制脏密码，导致修改了原密码
            userVO.setUserPwd(null);
            BeanUtils.copyProperties(userVO, user); // 更改基础信息
        }

        userMapper.updateById(user);

        return Resp.success();
    }

    @Override
    public Resp add(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userMapper.insert(user);

        return Resp.success();
    }

    @Override
    public Map<String, Object> info(String token) {

        User securityUser = RedisUtil.get("user");
        User user = userMapper.selectById(securityUser.getId());

        // 返回用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("name", user.getNickname());
        map.put("avatar", user.getAvatar());

        map.put("roles", user.getRoles());

        // Todo 暂时使用全部，用于前端调试
//        map.put("menus", menuService.findAll());
//        map.put("menus", menuMapper.getByRoleId(user.getRole().getId()));
        return map;
    }

    @Override
    public Resp list(PageDTO pageDTO) {
        if (pageDTO == null) {
            return Resp.NULL;
        }
        List<User> users = userMapper.list();
        return DataResp.ok(users);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }
}
