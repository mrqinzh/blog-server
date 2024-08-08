package com.mrqinzh.auth.session;

import com.mrqinzh.auth.RedirectStrategy;
import com.mrqinzh.auth.SecurityProperties;
import com.mrqinzh.auth.context.AuthenticationContextHolder;
import com.mrqinzh.auth.core.Token;
import com.mrqinzh.auth.util.WebUtils;
import org.springframework.core.annotation.Order;
import org.springframework.util.PathMatcher;
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
//@Component
@Order(SecurityProperties.DEFAULT_FILTER_ORDER + 50)
public class InvalidSessionFilter extends OncePerRequestFilter {

//    @Autowired
    private RedirectStrategy redirectStrategy;
//    @Autowired
    private SessionManager sessionManager;
//    @Autowired
    private PathMatcher pathMatcher;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (WebUtils.withoutAuthApi(request, pathMatcher)) {
            String tokenId = sessionManager.getTokenId(request);
            if (tokenId == null) {
                filterChain.doFilter(request, response);
                return;
            }
            // 检查令牌有无过期
//            if (!userLoginService.checkTokenExpired(tokenId)) {
//                filterChain.doFilter(request, response);
//                return;
//            }
            // todo 对于前后台接口没分开的时候，注意登录后token过期，前端请求过滤接口时，异常。
            // 让客户端删除cookie
            Cookie cookie = new Cookie(SecurityProperties.COOKIE_NAME, tokenId);
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            redirectStrategy.redirect(request, response);
        }

        String tokenId = sessionManager.getTokenId(request);
        if (!sessionManager.checkTokenId(tokenId)) {
            logger.info("token illegal ...");
            redirectStrategy.redirect(request, response);
            return;
        }

        Token token = AuthenticationContextHolder.getContext().getToken();
        if (token == null || !token.isAuthenticated()) {
            logger.info("token expired ...");
            redirectStrategy.redirect(request, response);
        }


        filterChain.doFilter(request, response);
    }

}
