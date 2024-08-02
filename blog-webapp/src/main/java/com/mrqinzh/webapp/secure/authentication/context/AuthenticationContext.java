package com.mrqinzh.webapp.secure.authentication.context;

import com.mrqinzh.commons.auth.Token;

public class AuthenticationContext {

    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
