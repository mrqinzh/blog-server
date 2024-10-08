package com.mrqinzh.framework.web.config;

import com.mrqinzh.framework.common.web.WebConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class WebApiProperties {

    private Api adminApi = new Api(WebConstant.ADMIN_API_PREFIX, "**.controller.admin.**");
    private Api appApi = new Api(WebConstant.APP_API_PREFIX, "**.controller.app.**");

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
