package com.mrqinzh.webapp.secure.authentication.token;

import com.mrqinzh.commons.auth.SecurityUser;

/**
 * 认证完成的token
 */
public class AuthenticatedToken extends AbstractAuthenticationToken<SecurityUser> {

    private String tokenId;
    private String username;
    private SecurityUser principal;

    public AuthenticatedToken(){}

    public AuthenticatedToken(String username, SecurityUser securityUser) {
        super();
        this.username = username;
        this.principal = securityUser;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public SecurityUser getPrincipal() {
        return principal;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPrincipal(SecurityUser principal) {
        this.principal = principal;
    }
}
