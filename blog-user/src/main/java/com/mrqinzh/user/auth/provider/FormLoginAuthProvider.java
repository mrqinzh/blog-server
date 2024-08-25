package com.mrqinzh.user.auth.provider;

import com.mrqinzh.user.auth.core.LoginType;
import com.mrqinzh.user.auth.core.SecurityUser;
import com.mrqinzh.user.auth.core.Token;
import com.mrqinzh.user.auth.exp.AuthException;
import com.mrqinzh.user.auth.service.UserLoginService;
import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.user.auth.token.AuthenticatedToken;
import com.mrqinzh.user.auth.token.UsernamePasswordToken;
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
        SecurityUser securityUser = loginService.loadUserByUsername(username);
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
