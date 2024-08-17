package com.mrqinzh.user.service;

import com.mrqinzh.common.entity.User;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.exception.BizException;
import com.mrqinzh.common.resp.PageResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.user.UserVO;
import com.mrqinzh.framework.utils.RedisUtil;
import com.mrqinzh.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<User> test() {
        return userMapper.selectAll();
    }

    @Override
    public Resp update(UserVO userVO) {

        User securityUser = (User) redisUtil.get("user"); // 当前登录的用户

        User user = new User();

        if (userVO.getUserPwd() != null && userVO.getNewPass() != null) {
            // 修改密码操作
            if (!userVO.getUserPwd().equals(securityUser.getPassword())) {
                throw new BizException(AppStatus.BAD_PARAMETER_REQUEST, "原密码发生了错误。。。");
            }
            // Todo 此处可以对密码进行加密。。。
            user.setUserPwd(userVO.getNewPass()); // 设置新密码
        } else {
            // 避免前端传入密码脏数据，导致BeanUtils.copyProperties 复制脏密码，导致修改了原密码
            userVO.setUserPwd(null);
            BeanUtils.copyProperties(userVO, user); // 更改基础信息
        }

        userMapper.updateById(user);

        return Resp.sendMsg(AppStatus.UPDATE_SUCCESS);
    }

    @Override
    public Resp add(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userMapper.insert(user);

        return Resp.sendMsg(AppStatus.INSERT_SUCCESS);
    }

    @Override
    public Map<String, Object> info(String token) {

        User securityUser = (User) redisUtil.get("user");
        User user = userMapper.selectById(securityUser.getId());

        // 返回用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("name", user.getUserNickname());
        map.put("avatar", user.getUserAvatar());

        map.put("roles", user.getRoles());

        // Todo 暂时使用全部，用于前端调试
//        map.put("menus", menuService.findAll());
//        map.put("menus", menuMapper.getByRoleId(user.getRole().getId()));
        return map;
    }

    @Override
    public Resp list(PageVO pageVO) {
        if (pageVO == null) {
            return new Resp();
        }
        List<User> users = userMapper.list();
        return PageResp.ok(users);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.selectById(id);
    }

}
