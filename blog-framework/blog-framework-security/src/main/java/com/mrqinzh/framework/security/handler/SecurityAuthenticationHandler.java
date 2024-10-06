package com.mrqinzh.framework.security.handler;

import cn.hutool.core.thread.NamedThreadFactory;
import com.mrqinzh.framework.common.security.LoginUser;
import com.mrqinzh.framework.common.security.StoreToken;
import com.mrqinzh.framework.common.security.UserDetailsImpl;
import com.mrqinzh.framework.common.utils.ServletUtil;
import com.mrqinzh.framework.security.utils.AuthenticationTokenCacheUtils;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SecurityAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler, LogoutSuccessHandler {

    private final ExecutorService executorService;
    @Resource
    private ObjectProvider<List<AuthenticationHandlerCustomizer>> handlersProvider;

    public SecurityAuthenticationHandler() {
        this.executorService = Executors.newSingleThreadExecutor(new NamedThreadFactory("authentication-handler-", false));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String tokenId = AuthenticationTokenCacheUtils.save(request, response, token2BO(authentication));
        writeResponse(request, response, DataResp.ok(tokenId));

        handlersProvider.ifAvailable(handlers -> {
            executorService.submit(() -> {
                handlers.forEach(handler -> handler.onAuthenticationSuccess(request, response, authentication));
            });
        });
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

        handlersProvider.ifAvailable(handlers -> {
            executorService.submit(() -> {
                handlers.forEach(handler -> handler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e));
            });
        });
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AuthenticationTokenCacheUtils.expire(httpServletRequest); // token过期
        writeResponse(httpServletRequest, httpServletResponse, Resp.success("退出成功了。"));

        handlersProvider.ifAvailable(handlers -> {
            executorService.submit(() -> {
                handlers.forEach(handler -> handler.onLogoutSuccess(httpServletRequest, httpServletResponse, authentication));
            });
        });
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
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "No-cache");

        ServletUtil.writeResponse(response, data);

    }

}
