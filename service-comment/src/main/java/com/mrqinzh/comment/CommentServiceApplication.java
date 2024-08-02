package com.mrqinzh.comment;

import com.mrqinzh.framework.annotation.BlogServerApplication;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@BlogServerApplication
@EnableDubbo(scanBasePackages = "com.mrqinzh.comment.rpc")
@EnableDiscoveryClient
public class CommentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentServiceApplication.class, args);
    }
}
