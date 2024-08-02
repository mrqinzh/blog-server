package com.mrqinzh.webapp.secure.authentication.handler;

import com.mrqinzh.webapp.utils.WebUtil;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class DefaultPreAuthHandler extends AbstractPrePostAuthHandler {

    @Override
    public boolean preAuth(HttpServletRequest request,
                           HttpServletResponse response,
                           AbstractAuthenticationToken<?> credentialToken) {

        credentialToken.setIp(WebUtil.getClientIp(request));
        return true;
    }

}
