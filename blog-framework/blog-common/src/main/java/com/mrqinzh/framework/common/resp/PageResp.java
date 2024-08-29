package com.mrqinzh.framework.common.resp;

import com.mrqinzh.framework.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@Setter
public class PageResp<T> extends Resp {

    public static final PageResp<?> NULL = new PageResp<>();

    private Response<T> data;

    private PageResp() {
    }

    private PageResp(long pageNum, long pageSize, long total, List<T> data) {
        super(ErrorCode.SUCCESS);
        this.data = new Response<>(pageNum, pageSize, total, data);
    }

    public static <T> PageResp<?> ok(long pageNum, long pageSize, long total, List<T> data) {
        return new PageResp<>(pageNum, pageSize, total, data);
    }

    public static <T> PageResp<?> ok(long pageNum, long pageSize, long total, List<T> data, Function<T, ?> mapping) {
        return new PageResp<>(pageNum, pageSize, total, data.stream().map(mapping).collect(Collectors.toList()));
    }

    @Data
    @AllArgsConstructor
    private static class Response<E> {
        private long pageNum;
        private long pageSize;
        private long total;

        private List<E> list;
    }

}
