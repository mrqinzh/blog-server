package com.mrqinzh.auth.event;

import com.mrqinzh.auth.exp.AuthException;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationListener extends AbstractAuthenticationListener {


    @Override
    protected void doAuthSuccessEvent(AuthSuccessEvent event) {
        System.out.println(this.getClass().getName() + " listener onApplicationEvent !");
        AbstractAuthenticationToken<?> source = event.getToken();

        // todo 执行业务
        // 缓存用户信息
        // 登录记录
    }

    @Override
    protected void doAuthFailureEvent(AuthFailureEvent event) {
        System.out.println(this.getClass().getName() + " listener onApplicationEvent !");
        // todo 执行业务
        AuthException exception = event.getException();
    }
}
