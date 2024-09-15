package com.mrqinzh.framework.common.web.utils;

import jakarta.servlet.http.HttpServletRequest;

public class WebFrameworkUtils {

    /**
     * 获取客户端的ip
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = "";
        if (request != null) {
            ip = request.getHeader("X-FORWARDED-FOR");
            if(ip==null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)){
                ip=request.getHeader("Proxy-Client-IP");
            }
            if(ip==null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)){
                ip=request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip==null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)){
                ip=request.getHeader("X-Real-IP");
            }
            if(ip==null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)){
                ip=request.getRemoteAddr();
            }
        }
        return ip;
    }

}
