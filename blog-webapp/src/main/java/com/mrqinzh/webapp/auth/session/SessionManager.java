package com.mrqinzh.webapp.auth.session;

import com.mrqinzh.webapp.auth.core.SecurityProperties;
import com.mrqinzh.webapp.auth.token.AuthenticatedToken;
import com.mrqinzh.framework.cache.AuthenticationCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class SessionManager {

    @Resource
    private AuthenticationCache cache;

    public String generateTokenId(AuthenticatedToken token) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void start(HttpServletRequest request, HttpServletResponse response, AuthenticatedToken token) {
        String tokenId = generateTokenId(token);
        token.setTokenId(tokenId);

        Cookie cookie = new Cookie(SecurityProperties.COOKIE_NAME, token.getTokenId());
        response.addCookie(cookie);

        cache.put(tokenId, token, SecurityProperties.DEFAULT_EXPIRE_TIME_SECONDS);
    }

    public AuthenticatedToken getToken(String sessionId) {
        return (AuthenticatedToken) cache.get(sessionId);
    }

    public String getTokenId(HttpServletRequest request) {
        // 顺序：请求参数 -> 请求头 -> 请求cookie
        String tokenId = request.getParameter(SecurityProperties.COOKIE_NAME);
        if (StringUtils.isBlank(tokenId)) {
            tokenId = request.getHeader(SecurityProperties.COOKIE_NAME);
        }
        if (StringUtils.isBlank(tokenId)) {
            Cookie cookie = WebUtils.getCookie(request, SecurityProperties.COOKIE_NAME);
            if (cookie != null) {
                return cookie.getValue();
            }
        }
        return tokenId;
    }

    public boolean checkTokenId(String tokenId) {
        return tokenId == null;
    }

    public void expire(AuthenticatedToken token) {
        if (token != null) {
            cache.delete(token.getTokenId());
        }
    }
}
