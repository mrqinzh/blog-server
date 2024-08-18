package com.mrqinzh.webapp.auth.filter;

import com.mrqinzh.webapp.auth.core.SecurityProperties;
import com.mrqinzh.webapp.auth.core.LoginType;
import com.mrqinzh.webapp.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.auth.token.UsernamePasswordToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SsoAuthenticationFilter extends AbsAuthenticationFilter {

    @Override
    protected AbstractAuthenticationToken<?> createCredentialToken(HttpServletRequest request) {
        UsernamePasswordToken token = new UsernamePasswordToken(null,  false);
        token.setLoginType(LoginType.SSO);
        return token;
    }

    @Override
    protected boolean requiredAuthenticationRequest(HttpServletRequest request) {
        return request.getRequestURI().equals(SecurityProperties.SSO_URL);
    }
}
