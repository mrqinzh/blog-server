package com.mrqinzh.webapp.auth.token;

public class UsernamePasswordToken extends AbstractAuthenticationToken<String> {

    private String username;
    private String password;

    public UsernamePasswordToken(String username, boolean authenticated) {
        this.username = username;
        super.setAuthenticated(authenticated);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPrincipal() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
