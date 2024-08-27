package com.mrqinzh.user.config;

import com.mrqinzh.framework.security.config.AuthorizeRequestsCustomizer;
import com.mrqinzh.user.domain.constant.ApiConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

@Configuration("userSecurityConfiguration")
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean("userAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry) {
                // TODO 这个每个项目都需要重复配置，得捉摸有没通用的方案
                // RPC 服务的安全配置
                registry.antMatchers(ApiConstant.PREFIX + "/**").permitAll();
            }

        };
    }

}
