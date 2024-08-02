package com.mrqinzh.webapp.secure.authentication.filter;

import com.mrqinzh.commons.auth.LoginType;
import com.mrqinzh.webapp.secure.authentication.AbsAuthenticationFilter;
import com.mrqinzh.webapp.secure.authentication.SecurityProperties;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.secure.authentication.token.UsernamePasswordToken;
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
