package com.mrqinzh.framework.mybatis;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
public class MybatisConfiguration {


    @Bean
    @Primary
    public MybatisPlusProperties mybatisPlusProperties() {
        MybatisPlusProperties properties = new MybatisPlusProperties();

        properties.setConfiguration(configuration());

        properties.setTypeAliasesPackage("com.mrqinzh.common.domain.entity");

        return properties;
    }

    public com.baomidou.mybatisplus.core.MybatisConfiguration configuration() {
        com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(false);

        configuration.addInterceptor(new PaginationInterceptor());

        return configuration;
    }

}
