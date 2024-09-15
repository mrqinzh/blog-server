package com.mrqinzh.framework.mybatis.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.resp.PageResp;

import java.util.function.Function;

public class PageUtils {

    public static <T, R> Page<R> convert(Page<T> page, Function<T, R> function) {
        Page<R> res = new Page<R>(page.getCurrent(), page.getSize(), page.getTotal());
        res.setPages(page.getPages());
        res.setRecords(page.getRecords().stream().map(function).toList());
        return res;
    }

    public static <R> PageResp<R> resp(Page<R> page) {
        return PageResp.ok(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    public static <T, R> PageResp<R> resp(Page<T> page, Function<T, R> mapping) {
        if (mapping == null) {
            throw new NullPointerException("mapping must not be null");
        }
        return PageResp.ok(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords(), mapping);
    }

}
