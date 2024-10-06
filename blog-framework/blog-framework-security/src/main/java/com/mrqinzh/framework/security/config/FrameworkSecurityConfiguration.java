package com.mrqinzh.framework.security.config;

import com.mrqinzh.framework.security.filter.AuthenticationTokenFilter;
import com.mrqinzh.framework.security.handler.AccessDeniedHandlerImpl;
import com.mrqinzh.framework.security.handler.AuthenticationEntryPointImpl;
import com.mrqinzh.framework.security.handler.SecurityAuthenticationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoConfigureOrder(-1) // 目的：先于 Spring Security 自动配置，避免一键改包后，org.* 基础包无法生效
@EnableMethodSecurity(securedEnabled = true)
public class FrameworkSecurityConfiguration {

    @Autowired(required = false)
    private List<AuthorizeRequestsCustomizer> authorizeRequestsCustomizers;

    @Bean
    public SecurityAuthenticationHandler defaultAuthenticationHandler() {
        return new SecurityAuthenticationHandler();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, SecurityAuthenticationHandler securityAuthenticationHandler) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        // 基于 token 机制，所以不需要 Session
        http.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(c ->
                c
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
        );

        // use .and()
        http.formLogin(formLogin ->
                formLogin
                        .loginProcessingUrl(SecurityProperties.LOGIN_URL)
                        .usernameParameter(SecurityProperties.USERNAME)
                        .passwordParameter(SecurityProperties.PASSWORD)
                        .successHandler(securityAuthenticationHandler)
                        .failureHandler(securityAuthenticationHandler));

        http.logout(logout ->
                logout
                        .logoutUrl(SecurityProperties.LOGOUT_URL)
                        .logoutSuccessHandler(securityAuthenticationHandler));

        // 添加各个模块的自定义过滤规则
        http.authorizeHttpRequests(authorize -> Optional.ofNullable(authorizeRequestsCustomizers).orElseGet(ArrayList::new).forEach(consumer -> consumer.customize(authorize)));
        // .anyRequest().authenticated() 跟上一行自定义配置 存在顺序问题
        http.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers(SecurityProperties.withoutAuthApis).permitAll()
                        .anyRequest().authenticated()
        );

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilter() {
        return new AuthenticationTokenFilter();
    }

    /**
     * 认证失败处理类 Bean
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 权限不够处理器 Bean
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

}
