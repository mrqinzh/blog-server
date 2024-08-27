package com.mrqinzh.framework.common.utils;

import cn.hutool.extra.spring.SpringUtil;

public class SpringContextHolder {

    public static <T> T getBean(Class<T> clazz) {
        return SpringUtil.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return SpringUtil.getBean(name, requiredType);
    }

    public static Object getBean(String beanName) {
        return SpringUtil.getBean(beanName);
    }

}
