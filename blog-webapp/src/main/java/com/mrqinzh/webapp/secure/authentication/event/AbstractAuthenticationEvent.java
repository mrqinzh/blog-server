package com.mrqinzh.webapp.secure.authentication.event;

import com.mrqinzh.webapp.secure.authentication.token.AbstractAuthenticationToken;
import org.springframework.context.ApplicationEvent;

public abstract class AbstractAuthenticationEvent extends ApplicationEvent {

    public AbstractAuthenticationEvent(AbstractAuthenticationToken<?> source) {
        super(source);
    }

    public AbstractAuthenticationToken<?> getToken() {
        return (AbstractAuthenticationToken<?>) super.getSource();
    }

}
