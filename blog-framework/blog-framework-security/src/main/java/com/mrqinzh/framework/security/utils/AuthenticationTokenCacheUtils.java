package com.mrqinzh.framework.security.utils;

import cn.hutool.core.util.IdUtil;
import com.mrqinzh.framework.common.constant.CacheKeyConstant;
import com.mrqinzh.framework.common.security.TokenStoreBO;
import com.mrqinzh.framework.security.config.SecurityProperties;
import com.mrqinzh.framework.redis.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationTokenCacheUtils {

    public static String generateTokenId() {
        return IdUtil.fastSimpleUUID();
    }

    public static String getTokenId(HttpServletRequest request) {
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

    public static String save(HttpServletRequest request, HttpServletResponse response, TokenStoreBO authentication) {
        String tokenId = generateTokenId();

        Cookie cookie = new Cookie(SecurityProperties.COOKIE_NAME, tokenId);
        response.addCookie(cookie);

        RedisUtil.set(getCacheKey(tokenId), authentication, SecurityProperties.DEFAULT_EXPIRE_TIME_SECONDS);

        return tokenId;
    }

    public static TokenStoreBO getToken(HttpServletRequest request) {
        String tokenId = getTokenId(request);
        return getToken(tokenId);
    }

    public static TokenStoreBO getToken(String tokenId) {
        String cacheKey = getCacheKey(tokenId);
        return RedisUtil.get(cacheKey);
    }

    public static void expire(String tokenId) {
        RedisUtil.expire(getCacheKey(tokenId), 0);
    }

    private static String getCacheKey(String tokenId) {
        return CacheKeyConstant.Auth.AUTH_TOKEN_PREFIX + tokenId;
    }
}
