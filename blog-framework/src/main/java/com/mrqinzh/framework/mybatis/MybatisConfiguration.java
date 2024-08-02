package com.mrqinzh.framework.mybatis;

import com.mrqinzh.framework.mybatis.id.AutoIncrIdGenerator;
import com.mrqinzh.framework.mybatis.id.IdGeneratorPlugin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

@Configuration
public class MybatisConfiguration {

    // 实体主键字段
    private final static String ID_FIELD = "id";


    @Bean
    public IdGeneratorPlugin idGeneratorPlugin() {
        return new IdGeneratorPlugin();
    }

    @Bean
    public AutoIncrIdGenerator autoIdGenerator() {
        return new AutoIncrIdGenerator();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, IdGeneratorPlugin idGeneratorPlugin) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
//        bean.setTypeAliases(BlogTypeAlias.class);

        bean.setTypeAliasesPackage("com.mrqinzh.common.entity");
        bean.setPlugins(idGeneratorPlugin);

        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        bean.setMapperLocations(
                Stream.of(
                        "classpath:mapper/*.xml"
                ).map(locationPattern -> {
                    try {
                        return resourcePatternResolver.getResources(locationPattern);
                    } catch (IOException e) {

                    }
                    return null;
                }).filter(Objects::nonNull).flatMap(Arrays::stream).toArray(Resource[]::new)
        );
        return bean.getObject();
    }

    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setAnnotationClass(Mapper.class);
        mapperScannerConfigurer.setBasePackage("com.mrqinzh");
        return mapperScannerConfigurer;
    }



}
