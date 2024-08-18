package com.mrqinzh.webapp.auth.processor;

import com.mrqinzh.webapp.auth.token.AbstractAuthenticationToken;
import com.mrqinzh.webapp.auth.token.AuthenticatedToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证前后置处理
 */
public abstract class AuthenticationPrePostProcessor {

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
