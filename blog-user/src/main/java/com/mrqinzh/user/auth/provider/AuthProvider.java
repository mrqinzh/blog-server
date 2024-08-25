package com.mrqinzh.user.auth.provider;


import com.mrqinzh.user.auth.core.Token;
import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.user.auth.token.AuthenticatedToken;

public interface AuthProvider {

    AuthenticatedToken doAuth(AbstractAuthenticationToken<?> credentialToken);

    boolean support(Token credentialToken);

}
