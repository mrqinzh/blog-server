package com.mrqinzh.comment;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.mrqinzh")
@EnableDubbo(scanBasePackages = "com.mrqinzh.comment.rpc")
@EnableDiscoveryClient
@MapperScan(basePackages = "com.mrqinzh.comment.mapper", annotationClass = Mapper.class)
public class CommentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentServiceApplication.class, args);
    }
}
