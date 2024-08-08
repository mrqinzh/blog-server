package com.mrqinzh.auth.handler;

import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultPreAuthHandler extends AbstractPrePostAuthHandler {

    @Override
    public boolean preAuth(HttpServletRequest request,
                           HttpServletResponse response,
                           AbstractAuthenticationToken<?> credentialToken) {

        credentialToken.setIp(WebUtils.getClientIp(request));
        return true;
    }

}
