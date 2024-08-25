package com.mrqinzh.user.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrqinzh.framework.common.domain.enums.AppStatus;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.user.auth.token.AuthenticatedToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public interface LogoutHandler {

    void logout(HttpServletRequest request, HttpServletResponse response);

    default void writeLogoutResponse(HttpServletRequest request, HttpServletResponse response, AuthenticatedToken token) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "No-cache");

        try {
            String message = new ObjectMapper().writeValueAsString(Resp.sendMsg(AppStatus.LOGOUT));
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
            OutputStream stream = response.getOutputStream();
            stream.write(bytes);
            stream.flush();
        } catch (IOException ignored) {

        }
    }
}
