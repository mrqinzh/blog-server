package com.mrqinzh.webapp.auth.event;

import com.mrqinzh.webapp.auth.token.AbstractAuthenticationToken;
import org.springframework.context.ApplicationEvent;

public abstract class AbstractAuthenticationEvent extends ApplicationEvent {

    public AbstractAuthenticationEvent(AbstractAuthenticationToken<?> source) {
        super(source);
    }

    public AbstractAuthenticationToken<?> getToken() {
        return (AbstractAuthenticationToken<?>) super.getSource();
    }

}
