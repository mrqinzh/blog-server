package com.mrqinzh.common.utils;

import com.mrqinzh.common.constant.MyConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class MyUtil {

    /**
     * 获取系统中的随机头像
     */
    public static String getRandomAvatarUrl() {
        return MyConstant.MY_HTTP + MyConstant.DOMAIN_IMG + "/avatar" + (int)Math.floor((Math.random() * 10) + 1) + ".png";
    }

    /**
     * 将 content 中的 HTML 标签过滤
     * @param content HTML
     * @return java.lang.String
     */
    public static String stripHtml(String content) {
        if (StringUtils.isEmpty(content)) return content;
        content = content.replaceAll("<p .*?>", "");
        content = content.replaceAll("<br\\s*/?>", "");
        content = content.replaceAll("\\<.*?>", "");
        return content;
    }

    /**
     * 获取指定范围内的随机数
     */
    public static int randomInt(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

}
