package com.mrqinzh.common.resp;

import com.mrqinzh.common.enums.AppStatus;
import lombok.Data;

import java.util.List;

// TODO: 2023/3/15 分页插件
@Data
public class PageResp<T> extends Resp {

    private List<T> data;

    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 起始行
     */
    private int startRow;
    /**
     * 末行
     */
    private int endRow;
    /**
     * 总数
     */
    private long total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 包含count查询
     */
    private boolean count = true;
    /**
     * 分页合理化
     */
    private Boolean reasonable;
    /**
     * 当设置为true的时候，如果pagesize设置为0（或RowBounds的limit=0），就不执行分页，返回全部结果
     */
    private Boolean pageSizeZero;
    /**
     * 进行count查询的列名
     */
    private String countColumn;
    /**
     * 排序
     */
    private String orderBy;
    /**
     * 只增加排序
     */
    private boolean orderByOnly;

    public static <T> PageResp<T> getInstance() {
        return new PageResp<>();
    }

    private PageResp() {}

    private PageResp(List<T> data) {
        super(AppStatus.SUCCESS);
        this.data = data;
    }

    public static <T> PageResp<T> ok(List<T> listData) {
        return new PageResp<>(listData);
    }

}
