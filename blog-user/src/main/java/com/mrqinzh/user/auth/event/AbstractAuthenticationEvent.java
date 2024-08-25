package com.mrqinzh.user.auth.event;

import com.mrqinzh.user.auth.token.AbstractAuthenticationToken;
import org.springframework.context.ApplicationEvent;

public abstract class AbstractAuthenticationEvent extends ApplicationEvent {

    public AbstractAuthenticationEvent(AbstractAuthenticationToken<?> source) {
        super(source);
    }

    public AbstractAuthenticationToken<?> getToken() {
        return (AbstractAuthenticationToken<?>) super.getSource();
    }

}
