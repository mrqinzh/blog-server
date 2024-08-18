package com.mrqinzh.webapp.auth.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

public abstract class AbstractAuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

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
        logger.info("doPreAuthEvent.......");
    }

    protected abstract void doAuthSuccessEvent(AuthSuccessEvent event);

    protected abstract void doAuthFailureEvent(AuthFailureEvent event);

}
