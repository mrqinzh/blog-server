package com.mrqinzh.framework.security.config;

import com.mrqinzh.framework.common.security.LoginUser;
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
        return requestTemplate -> {
            LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
            if (loginUser == null) {
                return;
            }
            String userStr = JsonUtils.toJsonString(loginUser);
            userStr = URLEncoder.encode(userStr, StandardCharsets.UTF_8); // 编码，避免中文乱码
            addHeader(requestTemplate, SecurityFrameworkUtils.LOGIN_USER_HEADER, userStr);
        };
    }

    private void addHeader(RequestTemplate requestTemplate, String name, String... values) {
        if (!requestTemplate.headers().containsKey(name)) {
            requestTemplate.header(name, values);
        }

    }

}
