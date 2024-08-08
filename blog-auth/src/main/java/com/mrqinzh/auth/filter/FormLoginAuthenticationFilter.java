package com.mrqinzh.auth.filter;

import com.mrqinzh.auth.AbsAuthenticationFilter;
import com.mrqinzh.auth.SecurityProperties;
import com.mrqinzh.auth.core.LoginType;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.token.UsernamePasswordToken;
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
