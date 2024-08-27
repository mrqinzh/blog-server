package com.mrqinzh.auth.config;

import com.mrqinzh.auth.handler.DefaultAuthenticationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration("authSecurityConfiguration")
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new DefaultAuthenticationHandler();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new DefaultAuthenticationHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new DefaultAuthenticationHandler();
    }

}
