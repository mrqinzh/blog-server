package com.mrqinzh.user.rpc.provider;

import com.mrqinzh.user.api.UserApiService;
import com.mrqinzh.user.domain.convert.UserConvert;
import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.user.UserRespDTO;
import com.mrqinzh.user.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class UserServiceProvider implements UserApiService {

    @Resource
    private UserService userService;

    @Override
    public UserRespDTO getByUsername(String username) {
        User user = userService.getByUsername(username);
        return UserConvert.INSTANCE.convert(user);
    }
}
