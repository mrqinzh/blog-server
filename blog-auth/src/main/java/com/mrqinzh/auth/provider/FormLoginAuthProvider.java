package com.mrqinzh.auth.provider;

import com.mrqinzh.auth.core.LoginType;
import com.mrqinzh.auth.core.SecurityUser;
import com.mrqinzh.auth.core.Token;
import com.mrqinzh.auth.exp.AuthException;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.token.AuthenticatedToken;
import com.mrqinzh.auth.token.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FormLoginAuthProvider implements AuthProvider {

//    @Autowired
//    private UserLoginService loginService;

    @Override
    public AuthenticatedToken doAuth(AbstractAuthenticationToken<?> credentialToken) {
        UsernamePasswordToken token;
        if (credentialToken instanceof UsernamePasswordToken) {
            token = (UsernamePasswordToken) credentialToken;
        } else throw new AuthException("token error !");
        String username = token.getUsername();
        String pwd = token.getPassword();
        SecurityUser securityUser = null;
//        SecurityUser securityUser = loginService.loadSecurityUserFromDb(username);
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
