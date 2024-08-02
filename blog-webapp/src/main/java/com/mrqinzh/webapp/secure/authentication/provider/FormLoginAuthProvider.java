package com.mrqinzh.webapp.secure.authentication.provider;

import com.mrqinzh.common.exception.AuthException;
import com.mrqinzh.commons.auth.LoginType;
import com.mrqinzh.commons.auth.SecurityUser;
import com.mrqinzh.commons.auth.Token;
import com.mrqinzh.webapp.secure.authentication.service.UserLoginService;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.secure.authentication.token.AuthenticatedToken;
import com.mrqinzh.webapp.secure.authentication.token.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormLoginAuthProvider implements AuthProvider {

    @Autowired
    private UserLoginService loginService;

    @Override
    public AuthenticatedToken doAuth(AbstractAuthenticationToken<?> credentialToken) {
        UsernamePasswordToken token;
        if (credentialToken instanceof UsernamePasswordToken) {
            token = (UsernamePasswordToken) credentialToken;
        } else throw new AuthException("token error !");

        String username = token.getUsername();
        String pwd = token.getPassword();
        SecurityUser securityUser = loginService.loadSecurityUserFromDb(username);
        if (pwd.equals(securityUser.getPassword())) {
            return new AuthenticatedToken(username, securityUser);
        }
        throw new AuthException("username or password error !");
    }

    @Override
    public boolean support(Token credentialToken) {
        return LoginType.FORM == credentialToken.getLoginType();
    }

}
