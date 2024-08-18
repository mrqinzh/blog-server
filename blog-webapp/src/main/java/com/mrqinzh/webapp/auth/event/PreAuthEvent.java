package com.mrqinzh.webapp.auth.event;


import com.mrqinzh.webapp.auth.token.AbstractAuthenticationToken;

public class PreAuthEvent extends AbstractAuthenticationEvent {

    public PreAuthEvent(AbstractAuthenticationToken<?> source) {
        super(source);
    }

}
