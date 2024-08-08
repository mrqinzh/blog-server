package com.mrqinzh.auth.provider;


import com.mrqinzh.auth.core.Token;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.token.AuthenticatedToken;

public interface AuthProvider {

    AuthenticatedToken doAuth(AbstractAuthenticationToken<?> credentialToken);

    boolean support(Token credentialToken);

}
