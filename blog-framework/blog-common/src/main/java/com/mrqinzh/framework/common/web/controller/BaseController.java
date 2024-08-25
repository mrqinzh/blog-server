package com.mrqinzh.framework.common.web.controller;

import com.mrqinzh.framework.common.web.utils.WebFrameworkUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getRequest();
    }

    protected final HttpServletResponse getResponse() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attributes.getResponse();
    }

    protected String getClientIp() {
        return WebFrameworkUtils.getClientIp(getRequest());
    }

}
