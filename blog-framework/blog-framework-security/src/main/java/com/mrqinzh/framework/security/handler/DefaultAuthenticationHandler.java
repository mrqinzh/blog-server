package com.mrqinzh.framework.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrqinzh.framework.common.security.LoginUser;
import com.mrqinzh.framework.common.security.StoreToken;
import com.mrqinzh.framework.common.security.UserDetailsImpl;
import com.mrqinzh.framework.security.utils.AuthenticationTokenCacheUtils;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import jakarta.servlet.ServletException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class DefaultAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String tokenId = AuthenticationTokenCacheUtils.save(request, response, token2BO(authentication));
        writeResponse(request, response, DataResp.ok(tokenId));
    }

    public StoreToken token2BO(Authentication authentication) {
        StoreToken bo = new StoreToken();
        bo.setAuthenticated(authentication.isAuthenticated());
        bo.setUser(new LoginUser((UserDetailsImpl) authentication.getPrincipal()));
        return bo;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        writeResponse(httpServletRequest, httpServletResponse, getResp(e));
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        writeResponse(httpServletRequest, httpServletResponse, Resp.success("退出成功了。"));
    }

    private Resp getResp(AuthenticationException e) {
        ErrorCode code = null;
        if (e instanceof LockedException) {
            code = ErrorCode.ACCOUNT_LOCKED;
        }
        // todo 完善其他情况
        return Optional.ofNullable(code).map(Resp::error).orElse(Resp.error(ErrorCode.USERNAME_PASSWORD_ERROR));
    }

    public static void writeResponse(HttpServletRequest request, HttpServletResponse response, Object data) {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "No-cache");

        try {
            String message = new ObjectMapper().writeValueAsString(data);
            byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
            OutputStream stream = response.getOutputStream();
            stream.write(bytes);
            stream.flush();
        } catch (IOException ignored) {

        }
    }

}
