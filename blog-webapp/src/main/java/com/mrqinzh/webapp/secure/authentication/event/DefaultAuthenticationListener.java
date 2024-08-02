package com.mrqinzh.webapp.secure.authentication.event;

import com.mrqinzh.common.exception.AuthException;
import com.mrqinzh.webapp.secure.authentication.service.UserLoginService;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationListener extends AbstractAuthenticationListener {

    @Autowired
    private UserLoginService userLoginService;

    @Override
    protected void doAuthSuccessEvent(AuthSuccessEvent event) {
        System.out.println(this.getClass().getName() + " listener onApplicationEvent !");
        AbstractAuthenticationToken<?> source = event.getToken();

        // todo 执行业务
        // 缓存用户信息
        userLoginService.cachePrincipal(source);
        // 登录记录
        userLoginService.addLoginRecord(source);
    }

    @Override
    protected void doAuthFailureEvent(AuthFailureEvent event) {
        System.out.println(this.getClass().getName() + " listener onApplicationEvent !");
        // todo 执行业务
        AuthException exception = event.getException();
    }
}
