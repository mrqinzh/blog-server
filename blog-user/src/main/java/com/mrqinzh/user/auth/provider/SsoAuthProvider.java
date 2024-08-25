package com.mrqinzh.user.auth.provider;

import com.mrqinzh.user.auth.core.LoginType;
import com.mrqinzh.user.auth.core.Token;
import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.user.auth.token.AuthenticatedToken;
import org.springframework.stereotype.Component;

@Component
public class SsoAuthProvider implements AuthProvider {

    @Override
    public AuthenticatedToken doAuth(AbstractAuthenticationToken credentialToken) {
        return new AuthenticatedToken(null, null);
    }

    @Override
    public boolean support(Token credentialToken) {
        return LoginType.SSO == credentialToken.getLoginType();
    }

}
