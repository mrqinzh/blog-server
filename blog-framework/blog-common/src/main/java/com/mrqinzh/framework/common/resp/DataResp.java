package com.mrqinzh.framework.common.resp;

import com.mrqinzh.framework.common.exception.ErrorCodeConstants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class DataResp<T> extends Resp {

    private T data;

    private DataResp(T data){
        super(ErrorCodeConstants.SUCCESS);
        this.data = data;
    }

    public static <T> DataResp<T> ok(T data) {
        return new DataResp<>(data);
    }
}
