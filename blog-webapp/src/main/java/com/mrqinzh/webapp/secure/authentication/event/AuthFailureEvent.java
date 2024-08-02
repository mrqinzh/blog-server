package com.mrqinzh.webapp.secure.authentication.event;


import com.mrqinzh.common.exception.AuthException;
import com.mrqinzh.common.utils.BizAssert;
import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;

public class AuthFailureEvent extends AbstractAuthenticationEvent {

    private AuthException exception;

    public AuthFailureEvent(AuthException exception, AbstractAuthenticationToken<?> source) {
        super(source);
        BizAssert.notNull(exception, "exception must be not null");
        this.exception = exception;
    }

    public AuthException getException() {
        return exception;
    }

}
