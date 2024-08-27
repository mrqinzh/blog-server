package com.mrqinzh.article.config;

import com.mrqinzh.comment.api.CommentApi;
import com.mrqinzh.user.api.UserApi;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {CommentApi.class, UserApi.class})
public class RpcConfiguration {
}
