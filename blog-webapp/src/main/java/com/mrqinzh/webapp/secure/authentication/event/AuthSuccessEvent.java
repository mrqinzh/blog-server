package com.mrqinzh.webapp.secure.authentication.event;

import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import org.springframework.util.Assert;

public class AuthSuccessEvent extends AbstractAuthenticationEvent {

    public AuthSuccessEvent(AbstractAuthenticationToken<?> source) {
        super(source);
        Assert.isTrue(source != null && source.isAuthenticated(), "token must be authenticated when auth success !");
    }

}
