package com.mrqinzh.auth.filter;

import com.mrqinzh.auth.AbsAuthenticationFilter;
import com.mrqinzh.auth.SecurityProperties;
import com.mrqinzh.auth.core.LoginType;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.token.UsernamePasswordToken;
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
