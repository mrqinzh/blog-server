package com.mrqinzh.user.auth.session;

import com.mrqinzh.framework.redis.utils.RedisUtil;
import com.mrqinzh.user.auth.core.SecurityProperties;
import com.mrqinzh.user.auth.token.AuthenticatedToken;
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

    public String generateTokenId(AuthenticatedToken token) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public void start(HttpServletRequest request, HttpServletResponse response, AuthenticatedToken token) {
        String tokenId = generateTokenId(token);
        token.setTokenId(tokenId);

        Cookie cookie = new Cookie(SecurityProperties.COOKIE_NAME, token.getTokenId());
        response.addCookie(cookie);

        RedisUtil.set(tokenId, token, 60);
    }

    public AuthenticatedToken getToken(HttpServletRequest request) {
        return (AuthenticatedToken) RedisUtil.get(getTokenId(request));
    }

    public AuthenticatedToken getToken(String sessionId) {
        return (AuthenticatedToken) RedisUtil.get(sessionId);
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
            RedisUtil.expire(token.getTokenId(), 0);
        }
    }
}
