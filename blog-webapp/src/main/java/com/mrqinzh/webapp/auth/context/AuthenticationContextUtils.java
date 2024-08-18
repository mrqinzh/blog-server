package com.mrqinzh.webapp.auth.context;

import com.mrqinzh.webapp.auth.core.SecurityUser;
import com.mrqinzh.webapp.auth.core.Token;
import com.mrqinzh.webapp.auth.token.AuthenticatedToken;

public class AuthenticationContextUtils {

    public static AuthenticatedToken getAuthenticatedToken() {
        Token token = AuthenticationContextHolder.getContext().getToken();
        return token != null ? (AuthenticatedToken) token : null;
    }

    public static String getAuthUsername() {
        Token token = AuthenticationContextHolder.getContext().getToken();
        return token.getUsername();
    }

    public static SecurityUser getSecurityUser() {
        Token token = AuthenticationContextHolder.getContext().getToken();
        return token != null ? (SecurityUser) token.getPrincipal() : null;
    }

}
