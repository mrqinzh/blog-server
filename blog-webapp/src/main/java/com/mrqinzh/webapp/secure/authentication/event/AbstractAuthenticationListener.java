package com.mrqinzh.webapp.secure.authentication.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public abstract class AbstractAuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {

    @Async
    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        if (event instanceof PreAuthEvent) {
            doPreAuthEvent((PreAuthEvent) event);
        } else if (event instanceof AuthSuccessEvent) {
            doAuthSuccessEvent((AuthSuccessEvent) event);
        } else if (event instanceof AuthFailureEvent) {
            doAuthFailureEvent((AuthFailureEvent) event);
        }

//        log.warn("error event at authentication");
    }

    protected void doPreAuthEvent(PreAuthEvent event) {
        // todo ...
        log.info("doPreAuthEvent.......");
    }

    protected abstract void doAuthSuccessEvent(AuthSuccessEvent event);

    protected abstract void doAuthFailureEvent(AuthFailureEvent event);

}
