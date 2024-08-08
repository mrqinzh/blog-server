package com.mrqinzh.auth.event;

import com.mrqinzh.auth.token.AbstractAuthenticationToken;
import org.springframework.util.Assert;

public class AuthSuccessEvent extends AbstractAuthenticationEvent {

    public AuthSuccessEvent(AbstractAuthenticationToken<?> source) {
        super(source);
        Assert.isTrue(source != null && source.isAuthenticated(), "token must be authenticated when auth success !");
    }

}
