package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.domain.page.PageRequest;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import com.mrqinzh.framework.redis.utils.RedisUtil;
import com.mrqinzh.user.dal.repo.UserRepository;
import com.mrqinzh.user.domain.bo.UserBO;
import com.mrqinzh.user.domain.convert.UserConvert;
import com.mrqinzh.user.domain.dto.UserStatisticsDTO;
import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.dto.UserRespDTO;
import com.mrqinzh.user.domain.vo.UserVO;
import com.mrqinzh.user.dal.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRepository userRepository;

    @Override
    public void update(UserVO userVO) {

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
    }

    @Override
    public void add(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        userMapper.insert(user);
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
    public Page<UserRespDTO> page(PageRequest pageReq) {
        Page<UserBO> page = userRepository.page(pageReq);
        return PageUtils.convert(page, UserConvert.INSTANCE::convert2RespDTO);
    }

    @Override
    public UserRespDTO getById(Long id) {
        UserBO userBO = userRepository.queryById(id);
        return UserConvert.INSTANCE.convert2DTO(userBO);
    }

    @Override
    public UserRespDTO getByUsername(String username) {
        UserBO userBO = userRepository.queryByUsername(username);
        return UserConvert.INSTANCE.convert2DTO(userBO);
    }

    @Override
    public long count() {
        return userRepository.selectCount();
    }

    @Override
    public UserStatisticsDTO statistics() {
        UserStatisticsDTO statisticsDTO = new UserStatisticsDTO();
        long count = userRepository.selectCount();

        statisticsDTO.setCount(count);
        return statisticsDTO;
    }
}
