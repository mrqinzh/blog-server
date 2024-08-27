package com.mrqinzh.user.rpc.provider;

import com.mrqinzh.user.api.UserApi;
import com.mrqinzh.user.domain.convert.UserConvert;
import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.user.UserRespDTO;
import com.mrqinzh.user.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserServiceProvider implements UserApi {

    @Resource
    private UserService userService;

    @Override
    public UserRespDTO getByUsername(String username) {
        User user = userService.getByUsername(username);
        return UserConvert.INSTANCE.convert(user);
    }
}
