package com.mrqinzh.auth.provider;

import com.mrqinzh.auth.core.LoginType;
import com.mrqinzh.auth.core.Token;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.token.AuthenticatedToken;
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
