package com.mrqinzh.auth.event;


import com.mrqinzh.auth.token.AbstractAuthenticationToken;

public class PreAuthEvent extends AbstractAuthenticationEvent {

    public PreAuthEvent(AbstractAuthenticationToken<?> source) {
        super(source);
    }

}
