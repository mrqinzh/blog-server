package com.mrqinzh.common.resp;

import com.mrqinzh.common.domain.enums.AppStatus;

public final class DataResp<T> extends Resp {

    private T data;

    private DataResp(T data){
        super(AppStatus.SUCCESS);
        this.data = data;
    }

    public static <T> DataResp<T> ok(T data) {
        return new DataResp<>(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
