package com.mrqinzh.user;

import com.mrqinzh.framework.annotation.BlogServerApplication;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@BlogServerApplication
@EnableDubbo(scanBasePackages = "com.mrqinzh.user.rpc")
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
