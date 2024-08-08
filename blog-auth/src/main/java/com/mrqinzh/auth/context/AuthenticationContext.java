package com.mrqinzh.auth.context;


import com.mrqinzh.auth.core.Token;

public class AuthenticationContext {

    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
