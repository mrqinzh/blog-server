package com.mrqinzh.webapp.service.impl;

import com.mrqinzh.common.entity.User;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.user.UserVO;
import com.mrqinzh.webapp.client.UserClient;
import com.mrqinzh.webapp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserClient userClient;

    @Override
    public List<User> test() {
        return userClient.test();
    }

    @Override
    public Resp update(UserVO userVO) {
        return userClient.update(userVO);
    }

    @Override
    public Resp add(UserVO userVO) {
        return userClient.add(userVO);
    }

    @Override
    public Map<String, Object> info(String token) {
        return userClient.info(token);
    }

    @Override
    public Resp list(PageVO pageVO) {
        return userClient.list(pageVO);
    }

    @Override
    public User getById(Integer id) {
        return userClient.getById(id);
    }
}
