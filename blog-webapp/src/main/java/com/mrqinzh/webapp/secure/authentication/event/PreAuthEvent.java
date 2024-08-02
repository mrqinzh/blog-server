package com.mrqinzh.webapp.secure.authentication.event;

import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;

public class PreAuthEvent extends AbstractAuthenticationEvent {

    public PreAuthEvent(AbstractAuthenticationToken<?> source) {
        super(source);
    }

}
