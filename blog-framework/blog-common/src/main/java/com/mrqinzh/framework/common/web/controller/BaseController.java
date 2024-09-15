package com.mrqinzh.framework.common.web.controller;

import com.mrqinzh.framework.common.utils.ServletUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final HttpServletRequest getRequest() {
        return ServletUtil.getRequest();
    }

    protected final HttpServletResponse getResponse() {
        return ServletUtil.getResponse();
    }

    protected String getClientIp() {
        return ServletUtil.getClientIp(getRequest());
    }

}
