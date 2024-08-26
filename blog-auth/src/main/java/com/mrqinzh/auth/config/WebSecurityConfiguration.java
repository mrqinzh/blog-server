package com.mrqinzh.auth.config;

import com.mrqinzh.auth.handler.DefaultAuthenticationHandler;
import com.mrqinzh.framework.common.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.sessionManagement().
        http.authorizeRequests()
                .antMatchers(SecurityProperties.withoutAuthApis).permitAll()
                .anyRequest().authenticated();
        // use .and()
        http.formLogin()
                .loginProcessingUrl(SecurityProperties.LOGIN_URL)
                .usernameParameter(SecurityProperties.USERNAME)
                .passwordParameter(SecurityProperties.PASSWORD)
                .successHandler(authenticationHandler())
                .failureHandler(authenticationHandler());
        http.logout()
                .logoutUrl(SecurityProperties.LOGOUT_URL)
                .logoutSuccessHandler(authenticationHandler());
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DefaultAuthenticationHandler authenticationHandler() {
        return new DefaultAuthenticationHandler();
    }
}
