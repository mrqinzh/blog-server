package com.mrqinzh.webapp.auth.context;

public class AuthenticationContextHolder {

    private static final ThreadLocal<AuthenticationContext> contextHolder = new ThreadLocal<>();

    public static AuthenticationContext getContext() {
        AuthenticationContext ctx = contextHolder.get();
        if (ctx == null) {
            ctx = new AuthenticationContext();
            contextHolder.set(ctx);
        }
        return ctx;
    }

    public static void clearContext() {
        contextHolder.remove();
    }

}
