package com.mrqinzh.article.api;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "article-service")
public interface TagApi {

}
