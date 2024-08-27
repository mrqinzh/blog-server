package com.mrqinzh.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrqinzh.framework.common.security.LoginUser;
import com.mrqinzh.framework.common.security.TokenStoreBO;
import com.mrqinzh.framework.common.security.UserDetailsImpl;
import com.mrqinzh.framework.security.utils.AuthenticationTokenCacheUtils;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.exception.ErrorCodeConstants;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    public TokenStoreBO token2BO(Authentication authentication) {
        TokenStoreBO bo = new TokenStoreBO();
        bo.setUser(new LoginUser((UserDetailsImpl) authentication.getPrincipal()));
        return bo;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        writeResponse(httpServletRequest, httpServletResponse, getResp(e));
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

    }

    private Resp getResp(AuthenticationException e) {
        ErrorCode code = null;
        if (e instanceof LockedException) {
            code = ErrorCodeConstants.ACCOUNT_LOCKED;
        }
        // todo 完善其他情况
        return Optional.ofNullable(code).map(Resp::new).orElse(new Resp(ErrorCodeConstants.USERNAME_PASSWORD_ERROR));
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
