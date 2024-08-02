package com.mrqinzh.webapp.secure.authentication.provider;

import com.mrqinzh.commons.auth.Token;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.secure.authentication.token.AuthenticatedToken;

public interface AuthProvider {

    AuthenticatedToken doAuth(AbstractAuthenticationToken<?> credentialToken);

    boolean support(Token credentialToken);

}
