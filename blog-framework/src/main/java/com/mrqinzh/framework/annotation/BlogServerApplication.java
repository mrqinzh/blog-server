package com.mrqinzh.framework.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@MapperScan(basePackages = "com.mrqinzh.mapper")
@SpringBootApplication(scanBasePackages = { "com.mrqinzh" })
public @interface BlogServerApplication {



}
