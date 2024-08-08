package com.mrqinzh.auth.event;


import com.mrqinzh.auth.exp.AuthException;
import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import org.springframework.util.Assert;

public class AuthFailureEvent extends AbstractAuthenticationEvent {

    private AuthException exception;

    public AuthFailureEvent(AuthException exception, AbstractAuthenticationToken<?> source) {
        super(source);
        Assert.notNull(exception, "exception must be not null");
        this.exception = exception;
    }

    public AuthException getException() {
        return exception;
    }

}
