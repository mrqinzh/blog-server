package com.mrqinzh.user.auth.filter;

import com.mrqinzh.user.auth.core.SecurityProperties;
import com.mrqinzh.user.auth.core.LoginType;
import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.user.auth.token.UsernamePasswordToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FormLoginAuthenticationFilter extends AbsAuthenticationFilter {

    @Override
    protected AbstractAuthenticationToken<?> createCredentialToken(HttpServletRequest request) {
        String username = request.getParameter(SecurityProperties.USERNAME);
        String password = request.getParameter(SecurityProperties.PASSWORD);

        UsernamePasswordToken token = new UsernamePasswordToken(username, false);
        token.setPassword(password);
        token.setLoginType(LoginType.FORM);
        return token;
    }

    @Override
    protected boolean requiredAuthenticationRequest(HttpServletRequest request) {
        return request.getRequestURI().equals(SecurityProperties.LOGIN_URL);
    }

}
