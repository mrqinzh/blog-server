package com.mrqinzh.comment.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.function.Function;

public class PageUtils {

    public static <T, R> Page<R> convert(Page<T> page, Function<T, R> function) {
        Page<R> res = new Page<R>(page.getCurrent(), page.getSize(), page.getTotal());
        res.setPages(page.getPages());
        res.setRecords(page.getRecords().stream().map(function).toList());
        return res;
    }

}
