package com.mrqinzh.webapp.secure.authentication.session;

import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.commons.auth.Token;
import com.mrqinzh.webapp.secure.authentication.RedirectStrategy;
import com.mrqinzh.webapp.secure.authentication.SecurityProperties;
import com.mrqinzh.webapp.secure.authentication.context.AuthenticationContextHolder;
import com.mrqinzh.webapp.secure.authentication.service.UserLoginService;
import com.mrqinzh.webapp.utils.WebUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 需在 SecurityContextFilter 后执行。
 */
@Component
@Order(SecurityProperties.DEFAULT_FILTER_ORDER + 50)
public class InvalidSessionFilter extends OncePerRequestFilter {

    @Autowired
    private RedirectStrategy redirectStrategy;
    @Autowired
    private SessionManager sessionManager;
    @Autowired
    private UserLoginService userLoginService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (WebUtil.withoutAuthApi(request)) {
            String tokenId = sessionManager.getTokenId(request);
            if (StringUtils.isBlank(tokenId)) {
                filterChain.doFilter(request, response);
                return;
            }
            if (!userLoginService.checkTokenExpired(tokenId)) {
                filterChain.doFilter(request, response);
                return;
            }
            // todo 对于前后台接口没分开的时候，注意登录后token过期，前端请求过滤接口时，异常。
            // 让客户端删除cookie
            Cookie cookie = new Cookie(SecurityProperties.COOKIE_NAME, tokenId);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            redirectStrategy.redirect(request, response, new Resp(AppStatus.TOKEN_EXPIRE_NO_REDIRECT));
        }

        String tokenId = sessionManager.getTokenId(request);
        if (!sessionManager.checkTokenId(tokenId)) {
            logger.info("token illegal ...");
            redirectStrategy.redirect(request, response, new Resp(AppStatus.TOKEN_ILLEGAL));
            return;
        }

        Token token = AuthenticationContextHolder.getContext().getToken();
        if (token == null || !token.isAuthenticated()) {
            logger.info("token expired ...");
            redirectStrategy.redirect(request, response, new Resp(AppStatus.TOKEN_EXPIRED));
        }


        filterChain.doFilter(request, response);
    }

}
