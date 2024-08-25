package com.mrqinzh.framework.common.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PageResp<T> extends Resp {

    private Response<T> data;

    public PageResp(long pageNum, long pageSize, long total, List<T> data) {
        this.data = new Response<>(pageNum, pageSize, total, data);
    }

    @Data
    @AllArgsConstructor
    public static class Response<T> {
        private long pageNum;
        private long pageSize;
        private long total;

        private List<T> list;
    }

}
