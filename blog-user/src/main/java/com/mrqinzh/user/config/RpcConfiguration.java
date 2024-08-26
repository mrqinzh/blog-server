package com.mrqinzh.user.config;

import com.mrqinzh.user.api.UserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = UserApi.class)
public class RpcConfiguration {
}
