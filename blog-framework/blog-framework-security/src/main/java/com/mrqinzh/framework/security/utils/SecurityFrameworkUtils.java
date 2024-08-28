package com.mrqinzh.framework.security.utils;

import com.mrqinzh.framework.common.security.LoginUser;
import com.mrqinzh.framework.common.security.TokenStoreBO;
import com.mrqinzh.framework.common.security.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

public class SecurityFrameworkUtils {

    /**
     * HEADER 认证头 value 的前缀
     */
    public static final String AUTHORIZATION_BEARER = "Bearer";

    public static final String LOGIN_USER_HEADER = "login-user";

    public static UserDetailsImpl getLoginUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return authentication.getPrincipal() instanceof UserDetailsImpl ? (UserDetailsImpl) authentication.getPrincipal() : null;
    }

    /**
     * 获得当前认证信息
     *
     * @return 认证信息
     */
    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }

    /**
     * 将基于token解析的认证信息放入springSecurity的上下文中
     */
    public static void setAuthentication(HttpServletRequest request, TokenStoreBO storeToken) {
        Authentication authentication = buildAuthentication(request, storeToken);
        setAuthentication(authentication);
    }

    /**
     * 将基于token解析的认证信息放入springSecurity的上下文中
     * @param authentication
     */
    public static void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static Authentication buildAuthentication(HttpServletRequest request, TokenStoreBO storeToken) {
        LoginUser user = storeToken.getUser();
        UserDetailsImpl userDetails = user.toUserDetails();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        return authenticationToken;
    }
}
