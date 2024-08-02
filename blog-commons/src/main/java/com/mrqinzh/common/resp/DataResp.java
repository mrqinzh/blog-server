package com.mrqinzh.common.resp;

import com.mrqinzh.common.enums.AppStatus;
import lombok.Data;

@Data
public final class DataResp<T> extends Resp {

    private T data;

    private DataResp(T data){
        super(AppStatus.SUCCESS);
        this.data = data;
    }

    public static <T> DataResp<T> ok(T data) {
        return new DataResp<>(data);
    }

}
