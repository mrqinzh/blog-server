package com.mrqinzh.user.auth.processor;

import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.user.auth.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultAuthenticationPreProcessor extends AuthenticationPrePostProcessor {

    @Override
    public boolean preAuth(HttpServletRequest request,
                           HttpServletResponse response,
                           AbstractAuthenticationToken<?> credentialToken) {

        credentialToken.setIp(WebUtils.getClientIp(request));
        return true;
    }

}
