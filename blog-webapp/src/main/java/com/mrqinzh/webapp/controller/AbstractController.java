package com.mrqinzh.webapp.controller;

import com.mrqinzh.webapp.auth.context.AuthenticationContextHolder;
import com.mrqinzh.webapp.auth.core.SecurityUser;
import com.mrqinzh.framework.cache.AuthenticationCache;
import com.mrqinzh.webapp.auth.core.Token;
import com.mrqinzh.webapp.auth.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public abstract class AbstractController {

    @Resource
    protected AuthenticationCache authCache;

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    protected final HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getResponse();
    }

    protected SecurityUser getCurrentUser() {
        Token token = AuthenticationContextHolder.getContext().getToken();
        return Optional.ofNullable(token).map(t -> (SecurityUser) t.getPrincipal()).orElse(null);
    }

    protected String getClientIp() {
        return WebUtils.getClientIp(getRequest());
    }

}
