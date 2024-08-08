package com.mrqinzh.auth.context;

import com.mrqinzh.auth.core.SecurityUser;
import com.mrqinzh.auth.core.Token;
import com.mrqinzh.auth.token.AuthenticatedToken;

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
