package com.mrqinzh.webapp.auth.context;


import com.mrqinzh.webapp.auth.core.Token;

public class AuthenticationContext {

    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
