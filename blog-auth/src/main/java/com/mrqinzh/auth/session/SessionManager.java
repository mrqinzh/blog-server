package com.mrqinzh.auth.session;

import com.mrqinzh.auth.SecurityProperties;
import com.mrqinzh.auth.token.AuthenticatedToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class SessionManager {

//    @Autowired
//    private RedisUtil redisUtil;

    /**
     * todo 后续根据jwt的规则生成
     */
    public String generateTokenId(AuthenticatedToken token) {
        return SecurityProperties.TOKEN_CACHE_PREFIX +
                UUID.randomUUID().toString().replaceAll("-", "") +
                token.getUsername();
    }

    public void start(HttpServletRequest request, HttpServletResponse response, AuthenticatedToken token) {
        String tokenId = generateTokenId(token);
        token.setTokenId(tokenId);

        Cookie cookie = new Cookie(SecurityProperties.COOKIE_NAME, token.getTokenId());
        response.addCookie(cookie);

//        redisUtil.set(tokenId, token, SecurityProperties.DEFAULT_EXPIRE_TIME_SECONDS);
    }

    public AuthenticatedToken getToken(String sessionId) {
//        return (AuthenticatedToken) redisUtil.get(sessionId);
        return null;
    }

    public String getTokenId(HttpServletRequest request) {
        // 顺序：请求参数 -> 请求头 -> 请求cookie
        String tokenId = request.getParameter(SecurityProperties.COOKIE_NAME);
//        if (StringUtils.isBlank(tokenId)) {
//            tokenId = request.getHeader(SecurityProperties.COOKIE_NAME);
//        }
//        if (StringUtils.isBlank(tokenId)) {
//            Cookie cookie = WebUtils.getCookie(request, SecurityProperties.COOKIE_NAME);
//            if (cookie != null) {
//                return cookie.getValue();
//            }
//        }
        return tokenId;
    }

    public boolean checkTokenId(String tokenId) {
        return tokenId == null || tokenId.startsWith(SecurityProperties.TOKEN_CACHE_PREFIX);
    }

    public void expire(AuthenticatedToken token) {
//        if (token != null) {
//            String username = token.getUsername();
//            redisUtil.expire(username, 0);
//            redisUtil.expire(token.getTokenId(), 0);
//        }
    }
}
