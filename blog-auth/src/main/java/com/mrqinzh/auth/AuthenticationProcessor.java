package com.mrqinzh.auth;

import com.mrqinzh.auth.exp.AuthException;
import com.mrqinzh.auth.provider.AuthProvider;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.token.AuthenticatedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthenticationProcessor {

    @Autowired
    private List<AuthProvider> providers;

    public AuthenticatedToken auth(AbstractAuthenticationToken<?> credential) {
        for (AuthProvider provider : providers) {
            if (provider.support(credential)) {
                AuthenticatedToken authResult = provider.doAuth(credential);
                copyDetails(credential, authResult);
                return authResult;
            }
        }
        throw new AuthException("no providers for auth !");
    }

    private void copyDetails(AbstractAuthenticationToken<?> credential, AuthenticatedToken authenticated) {
        authenticated.setLoginType(credential.getLoginType());
        authenticated.setIp(credential.getIp());
    }

}
