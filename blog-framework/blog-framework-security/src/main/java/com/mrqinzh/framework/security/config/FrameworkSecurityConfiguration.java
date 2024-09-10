package com.mrqinzh.framework.security.config;

import com.mrqinzh.framework.security.filter.AuthenticationTokenFilter;
import com.mrqinzh.framework.security.handler.AccessDeniedHandlerImpl;
import com.mrqinzh.framework.security.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AutoConfigureOrder(Integer.MIN_VALUE) // 目的：先于 Spring Security 自动配置，避免一键改包后，org.* 基础包无法生效
@EnableMethodSecurity(securedEnabled = true)
public class FrameworkSecurityConfiguration {

    @Autowired(required = false)
    private List<AuthorizeRequestsCustomizer> authorizeRequestsCustomizers;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        // 基于 token 机制，所以不需要 Session
        http.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(c -> c.authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler()));

        // use .and()
        http.formLogin()
                .loginProcessingUrl(SecurityProperties.LOGIN_URL)
                .usernameParameter(SecurityProperties.USERNAME)
                .passwordParameter(SecurityProperties.PASSWORD)
                .successHandler(successHandler())
                .failureHandler(failureHandler());

        http.logout()
                .logoutUrl(SecurityProperties.LOGOUT_URL)
                .logoutSuccessHandler(logoutSuccessHandler());

        http.authorizeRequests()
                .requestMatchers(SecurityProperties.withoutAuthApis).permitAll()
                .and()
                // 添加各个模块的自定义过滤规则
                .authorizeRequests(registry -> Optional.ofNullable(authorizeRequestsCustomizers).orElseGet(ArrayList::new).forEach(customizer -> customizer.customize(registry)))
                .authorizeRequests()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilter() {
        return new AuthenticationTokenFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new SimpleUrlLogoutSuccessHandler();
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
