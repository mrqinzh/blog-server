package com.mrqinzh.webapp.auth.provider;

import com.mrqinzh.webapp.auth.core.LoginType;
import com.mrqinzh.webapp.auth.core.Token;
import com.mrqinzh.webapp.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.auth.token.AuthenticatedToken;
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
