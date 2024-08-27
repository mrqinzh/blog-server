package com.mrqinzh.framework.security.config;

import com.mrqinzh.framework.common.utils.JsonUtils;
import com.mrqinzh.framework.common.security.UserDetailsImpl;
import com.mrqinzh.framework.security.utils.SecurityFrameworkUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class RpcSecurityConfiguration {

    @Bean
    public RequestInterceptor rpcSecurityInterceptor() {
        return new RequestInterceptor() {

            @Override
            @SneakyThrows
            public void apply(RequestTemplate requestTemplate) {
                UserDetailsImpl loginUser = SecurityFrameworkUtils.getLoginUser();
                if (loginUser == null) {
                    return;
                }
                try {
                    String userStr = JsonUtils.toJsonString(loginUser);
                    userStr = URLEncoder.encode(userStr, StandardCharsets.UTF_8.name()); // 编码，避免中文乱码
                    requestTemplate.header(SecurityFrameworkUtils.LOGIN_USER_HEADER, userStr);
                } catch (Exception ex) {
                    log.error("[apply][序列化 LoginUser({}) 发生异常]", loginUser, ex);
                    throw ex;
                }
            }
        };
    }

}
