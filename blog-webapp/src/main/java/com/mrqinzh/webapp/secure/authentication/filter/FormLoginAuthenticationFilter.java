package com.mrqinzh.webapp.secure.authentication.filter;

import com.mrqinzh.common.utils.BizAssert;
import com.mrqinzh.commons.auth.LoginType;
import com.mrqinzh.webapp.secure.authentication.AbsAuthenticationFilter;
import com.mrqinzh.webapp.secure.authentication.SecurityProperties;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.secure.authentication.token.UsernamePasswordToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class FormLoginAuthenticationFilter extends AbsAuthenticationFilter {

    @Override
    protected AbstractAuthenticationToken<?> createCredentialToken(HttpServletRequest request) {
        String username = request.getParameter(SecurityProperties.USERNAME);
        String password = request.getParameter(SecurityProperties.PASSWORD);
        BizAssert.isNotBlank("parameter error !", username, password);

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
