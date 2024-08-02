package com.mrqinzh.framework.mybatis.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.mrqinzh.common.resp.PageResp;

import java.util.List;

public class PageDTO<E> extends Page<E> {

    public static <E> PageResp<E> toPageResp(List<E> data, long total) {
        PageInfo<E> info = new PageInfo<>(data);

        PageResp<E> pageResp = PageResp.getInstance();
        pageResp.setPageNum(info.getPageNum());
        pageResp.setPages(info.getPages());
        pageResp.setPageSize(info.getPageSize());
        pageResp.setData(info.getList());
        pageResp.setTotal(total);
        return pageResp;
    }

    public static <E> PageResp<E> empty() {
        return PageResp.getInstance();
    }

}
