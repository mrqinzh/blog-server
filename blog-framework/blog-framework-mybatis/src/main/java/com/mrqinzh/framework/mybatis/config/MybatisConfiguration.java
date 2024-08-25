package com.mrqinzh.framework.mybatis.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.mrqinzh.framework.mybatis.entity.BlogTypeAliases;
import com.mrqinzh.framework.mybatis.interceptor.BaseEntityFieldInterceptor;
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
        properties.setGlobalConfig(globalConfig());

        properties.setTypeAliasesSuperType(BlogTypeAliases.class);
        properties.setTypeAliasesPackage("com.mrqinzh.*.domain");

        return properties;
    }

    public GlobalConfig globalConfig() {
        GlobalConfig config = GlobalConfigUtils.defaults();
        config.setIdentifierGenerator(new DefaultIdentifierGenerator());
        config.setBanner(false);
        return config;
    }

    public com.baomidou.mybatisplus.core.MybatisConfiguration configuration() {
        com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(false);

        configuration.addInterceptor(new BaseEntityFieldInterceptor());
        configuration.addInterceptor(new PaginationInterceptor());

        return configuration;
    }

}
