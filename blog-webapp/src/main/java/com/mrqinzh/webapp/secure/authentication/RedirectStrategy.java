package com.mrqinzh.webapp.secure.authentication;

import com.mrqinzh.common.resp.Resp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RedirectStrategy {

    void redirect(HttpServletRequest request, HttpServletResponse response, Resp resp);

}
