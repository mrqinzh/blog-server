package com.mrqinzh.user.auth.service;

import com.mrqinzh.user.auth.core.SecurityUser;

public interface UserLoginService {

    SecurityUser loadUserByUsername(String username);

}
