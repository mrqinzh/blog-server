package com.mrqinzh.user.auth.event;


import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;

public class PreAuthEvent extends AbstractAuthenticationEvent {

    public PreAuthEvent(AbstractAuthenticationToken<?> source) {
        super(source);
    }

}
