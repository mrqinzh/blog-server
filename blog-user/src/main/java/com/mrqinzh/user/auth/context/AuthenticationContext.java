package com.mrqinzh.user.auth.context;


import com.mrqinzh.user.auth.core.Token;

public class AuthenticationContext {

    private Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
