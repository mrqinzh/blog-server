package com.mrqinzh.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RedirectStrategy {

    void redirect(HttpServletRequest request, HttpServletResponse response);

}
