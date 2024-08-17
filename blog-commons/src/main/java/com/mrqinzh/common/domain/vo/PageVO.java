package com.mrqinzh.common.domain.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageVO<T> {

    private List<T> list;
    private int total;
    private int pageNum;
    private int pageSize;

    public PageVO() {
    }

    public PageVO(Page<T> page) {
        this.list = page.getRecords();
        this.total = (int) page.getTotal();
        this.pageNum = (int) page.getCurrent();
        this.pageSize = (int) page.getSize();
    }

    /**
     * 将page中 T 类型的 records 对象，通过 typeConvertFun 转化为指定类型
     */
    public static <P, R> PageVO<R> convert(Page<P> page, Function<P, R> typeConvertFun) {
        PageVO<R> vo = new PageVO<>();
        vo.setList(page.getRecords().stream().map(typeConvertFun).collect(Collectors.toList()));
        vo.setTotal((int) page.getTotal());
        vo.setPageNum((int) page.getCurrent());
        vo.setPageSize((int) page.getSize());
        return vo;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
