package com.mrqinzh.user.auth.event;

import com.mrqinzh.user.auth.exp.AuthException;
import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationListener extends AbstractAuthenticationListener {


    @Override
    protected void doAuthSuccessEvent(AuthSuccessEvent event) {
        logger.info(this.getClass().getName() + " listener doAuthSuccessEvent !");
        AbstractAuthenticationToken<?> source = event.getToken();

        // todo 执行业务
        // 缓存用户信息
        // 登录记录
    }

    @Override
    protected void doAuthFailureEvent(AuthFailureEvent event) {
        logger.info(this.getClass().getName() + " listener doAuthFailureEvent !");
        // todo 执行业务
        AuthException exception = event.getException();
    }
}
