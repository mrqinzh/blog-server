package com.mrqinzh.webapp.auth.provider;


import com.mrqinzh.webapp.auth.core.Token;
import com.mrqinzh.webapp.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.auth.token.AuthenticatedToken;

public interface AuthProvider {

    AuthenticatedToken doAuth(AbstractAuthenticationToken<?> credentialToken);

    boolean support(Token credentialToken);

}
