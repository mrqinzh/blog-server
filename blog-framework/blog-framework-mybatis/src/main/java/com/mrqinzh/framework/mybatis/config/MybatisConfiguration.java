package com.mrqinzh.framework.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
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

    public MybatisPlusProperties.CoreConfiguration configuration() {
        MybatisPlusProperties.CoreConfiguration coreConfiguration = new MybatisPlusProperties.CoreConfiguration();

        com.baomidou.mybatisplus.core.MybatisConfiguration configuration = new com.baomidou.mybatisplus.core.MybatisConfiguration();
        configuration.setMapUnderscoreToCamelCase(false);
        configuration.addInterceptor(new BaseEntityFieldInterceptor());
        configuration.addInterceptor(mybatisPlusInterceptor()); // mybatis-plus 对 mybatis 的 interceptor 进行了二次封装

        coreConfiguration.applyTo(configuration);
        return coreConfiguration;
    }


    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
