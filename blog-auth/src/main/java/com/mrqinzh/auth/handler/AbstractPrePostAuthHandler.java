package com.mrqinzh.auth.handler;

import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.auth.token.AuthenticatedToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证前后置处理
 */
public abstract class AbstractPrePostAuthHandler {

    public boolean preAuth(HttpServletRequest request,
                           HttpServletResponse response,
                           AbstractAuthenticationToken<?> credentialToken) {
        return true;
    }

    public boolean postAuth(HttpServletRequest request,
                            HttpServletResponse response,
                            AuthenticatedToken authenticated) {
        return true;
    }
}
