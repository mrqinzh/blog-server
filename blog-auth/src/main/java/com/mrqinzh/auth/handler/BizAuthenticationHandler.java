package com.mrqinzh.auth.handler;

import cn.hutool.http.useragent.UserAgentParser;
import com.mrqinzh.auth.domain.bo.LoginLogBO;
import com.mrqinzh.auth.service.LoginLogService;
import com.mrqinzh.framework.security.core.UserDetailsImpl;
import com.mrqinzh.framework.common.utils.ServletUtil;
import com.mrqinzh.framework.security.handler.AuthenticationHandlerCustomizer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BizAuthenticationHandler implements AuthenticationHandlerCustomizer {

    private final LoginLogService loginLogService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 记录登录日志
        this.addAuthSuccessLog(request, authentication);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {

    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {

    }

    private void addAuthSuccessLog(HttpServletRequest request, Authentication authentication) {
        LoginLogBO loginLogBO = new LoginLogBO();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();

        loginLogBO.setUserId(userDetails.getId());
        loginLogBO.setIp(ServletUtil.getClientIp(request));

        Optional.ofNullable(UserAgentParser.parse(request.getHeader("User-Agent"))).ifPresent(userAgent -> {
            loginLogBO.setBrowser(userAgent.getBrowser().toString());
            loginLogBO.setOs(userAgent.getOs().toString());
            loginLogBO.setDevice(userAgent.getPlatform().toString());
        });


        loginLogService.create(loginLogBO);
    }
}
