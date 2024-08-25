package com.mrqinzh.user.auth.util;

import com.mrqinzh.user.auth.core.SecurityProperties;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    /**
     * 是否不需要登录请求
     * @return true 是；false 否
     */
    public static boolean withoutAuthApi(HttpServletRequest request, PathMatcher pathMatcher) {
        String requestURI = request.getRequestURI();
        for (String allowURL : SecurityProperties.accessApisWithoutAuth) {
            if (pathMatcher.match(allowURL, requestURI)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取客户端的ip
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = "";
        if (request != null) {
            ip = request.getHeader("X-FORWARDED-FOR");
            if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
                ip=request.getHeader("Proxy-Client-IP");
            }
            if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
                ip=request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
                ip=request.getHeader("X-Real-IP");
            }
            if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
                ip=request.getRemoteAddr();
            }
        }
        return ip;
    }


}
