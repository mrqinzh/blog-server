package com.mrqinzh.framework.web.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ApiConfiguration {

    private Api adminApi = new Api("/admin-api", "**.controller.admin.**");
    private Api appApi = new Api("/app-api", "**.controller.app.**");

    @Data
    @AllArgsConstructor
    public static class Api {
        /**
         * url前缀
         */
        private String prefix;
        /**
         * 包路径
         */
        private String path;
    }

}
