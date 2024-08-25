package com.mrqinzh.user.auth.token;


import com.mrqinzh.user.auth.core.LoginType;
import com.mrqinzh.user.auth.core.Token;

public abstract class AbstractAuthenticationToken<T> implements Token {

    private boolean authenticated;
    private LoginType loginType;
    private String ip;

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public abstract T getPrincipal();

    @Override
    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
