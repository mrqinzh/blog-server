package com.mrqinzh.webapp.secure.authentication.context;

import com.mrqinzh.common.entity.User;
import com.mrqinzh.commons.auth.SecurityUser;
import com.mrqinzh.commons.auth.Token;
import com.mrqinzh.webapp.secure.authentication.token.AuthenticatedToken;

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

    public static User getUser() {
        Token token = AuthenticationContextHolder.getContext().getToken();
        if (token == null || token.getPrincipal() == null) return null;
        return (User) token.getPrincipal();
    }

}
