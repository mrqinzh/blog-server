package com.mrqinzh.framework.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private WebApiProperties webApiProperties;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurePathMatch(configurer, webApiProperties.getAdminApi());
        configurePathMatch(configurer, webApiProperties.getAppApi());
        configurePathMatch(configurer, webApiProperties.getAuthApi());
    }

    /**
     * 设置 API 前缀，仅仅匹配 controller 包下的
     *
     * @param configurer 配置
     * @param api        API 配置
     */
    private void configurePathMatch(PathMatchConfigurer configurer, WebApiProperties.Api api) {
        AntPathMatcher antPathMatcher = new AntPathMatcher(".");
        configurer.addPathPrefix(api.getPrefix(), clazz -> clazz.isAnnotationPresent(RestController.class)
                && antPathMatcher.match(api.getPath(), clazz.getPackage().getName())); // 仅仅匹配 controller 包
    }

}
