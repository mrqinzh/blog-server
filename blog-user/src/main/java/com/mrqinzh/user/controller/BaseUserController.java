package com.mrqinzh.user.controller;

import com.mrqinzh.framework.common.security.LoginUser;
import com.mrqinzh.framework.common.security.StoreToken;
import com.mrqinzh.framework.common.web.controller.BaseController;
import com.mrqinzh.framework.security.utils.AuthenticationTokenCacheUtils;

import java.util.Optional;

public abstract class BaseUserController extends BaseController {


    protected final LoginUser getUser() {
        StoreToken token = AuthenticationTokenCacheUtils.getToken(getRequest());
        return Optional.ofNullable(token).map(StoreToken::getUser).orElse(null);
    }

}
