package com.mrqinzh.webapp.utils;


import com.mrqinzh.framework.utils.SpringContextHolder;
import com.mrqinzh.webapp.secure.authentication.SecurityProperties;
import org.springframework.util.PathMatcher;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {

    /**
     * 获取request对象
     * @return
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response对象
     * @return
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
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

    /**
     * 是否不需要登录请求
     * @return true 是；false 否
     */
    public static boolean withoutAuthApi(HttpServletRequest request) {
        PathMatcher pathMatcher = SpringContextHolder.getBean(PathMatcher.class);
        String requestURI = request.getRequestURI();
        for (String allowURL : SecurityProperties.accessApisWithoutAuth) {
            if (pathMatcher.match(allowURL, requestURI)) {
                return true;
            }
        }
        return false;
    }

}