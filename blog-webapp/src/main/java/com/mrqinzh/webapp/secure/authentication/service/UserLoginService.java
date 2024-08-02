package com.mrqinzh.webapp.secure.authentication.service;

import com.mrqinzh.apis.login.LoginLogService;
import com.mrqinzh.apis.user.UserService;
import com.mrqinzh.commons.auth.SecurityUser;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

//    @DubboReference
    private UserService userService;
//    @DubboReference
    private LoginLogService loginLogService;

    public void cachePrincipal(AbstractAuthenticationToken<?> source) {

    }

    public void addLoginRecord(AbstractAuthenticationToken<?> source) {

    }

    public SecurityUser loadSecurityUserFromDb(String username) {

        return null;
    }

    public boolean checkTokenExpired(String tokenId) {

        return false;
    }
}
