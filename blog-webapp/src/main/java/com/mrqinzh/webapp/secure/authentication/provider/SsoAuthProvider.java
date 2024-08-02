package com.mrqinzh.webapp.secure.authentication.provider;

import com.mrqinzh.commons.auth.LoginType;
import com.mrqinzh.commons.auth.Token;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.secure.authentication.token.AuthenticatedToken;
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
