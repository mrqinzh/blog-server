package com.mrqinzh.framework.security.handler;

import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.utils.ServletUtil;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 访问一个需要认证的 URL 资源，已经认证（登录）但是没有权限的情况下，返回 {@link GlobalErrorCodeConstants#FORBIDDEN} 错误码。
 *
 * 补充：Spring Security 通过 {@link ExceptionTranslationFilter#handleAccessDeniedException(HttpServletRequest, HttpServletResponse, FilterChain, AccessDeniedException)} 方法，调用当前类
 *
 * @author 芋道源码
 */
@Slf4j
@SuppressWarnings("JavadocReference")
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException, ServletException {
        // 打印 warn 的原因是，不定期合并 warn，看看有没恶意破坏
        // 返回 403
        ServletUtil.writeJSON(response, Resp.error(ErrorCode.NO_PERMISSION));
    }

}
