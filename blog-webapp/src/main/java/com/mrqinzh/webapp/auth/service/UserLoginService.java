package com.mrqinzh.webapp.auth.service;

import com.mrqinzh.webapp.auth.core.SecurityUser;

public interface UserLoginService {

    SecurityUser loadUserByUsername(String username);

}
