package com.mrqinzh.user.auth.service;

import com.mrqinzh.user.auth.core.SecurityUser;
import com.mrqinzh.user.auth.entity.AuthUser;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Override
    public SecurityUser loadUserByUsername(String username) {
        AuthUser authUser = new AuthUser();
        // todo rpc userService
        authUser.setId(1);
        authUser.setUserName(username);
        authUser.setUserPwd("1");
        return authUser;
    }

}
