package com.mrqinzh.webapp.client.impl;

import com.mrqinzh.apis.user.UserService;
import com.mrqinzh.common.domain.entity.User;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.vo.user.UserVO;
import com.mrqinzh.webapp.client.UserClient;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserClientImpl implements UserClient {

    @DubboReference(timeout = 2000)
    private UserService userService;

    @Override
    public List<User> test() {
        return userService.test();
    }

    @Override
    public Resp update(UserVO userVO) {
        return userService.update(userVO);
    }

    @Override
    public Resp add(UserVO userVO) {
        return userService.add(userVO);
    }

    @Override
    public Map<String, Object> info(String token) {
        return userService.info(token);
    }

    @Override
    public Resp list(PageDTO pageDTO) {
        return userService.list(pageDTO);
    }

    @Override
    public User getById(Integer id) {
        return userService.getById(id);
    }
}
