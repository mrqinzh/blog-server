package com.mrqinzh.auth.session;

import cn.hutool.core.util.IdUtil;
import com.mrqinzh.framework.common.security.SecurityProperties;
import com.mrqinzh.framework.redis.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SessionManager {

    public String generateTokenId(Authentication authentication) {
        return IdUtil.fastSimpleUUID();
    }

    public String start(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String tokenId = generateTokenId(authentication);

        Cookie cookie = new Cookie(SecurityProperties.COOKIE_NAME, tokenId);
        response.addCookie(cookie);

        RedisUtil.set(tokenId, authentication, 60);

        return tokenId;
    }

    public Authentication getToken(HttpServletRequest request) {
        return RedisUtil.get(getTokenId(request));
    }

    public Authentication getToken(String sessionId) {
        return RedisUtil.get(sessionId);
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

    public void expire(String tokenId) {
        RedisUtil.expire(tokenId, 0);
    }
}
