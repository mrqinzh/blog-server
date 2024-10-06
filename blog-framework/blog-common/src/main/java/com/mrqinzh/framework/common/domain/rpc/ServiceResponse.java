package com.mrqinzh.framework.common.domain.rpc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * rpc 返回的结果对象
 * @param <T>
 */
@Getter
@Setter
@AllArgsConstructor
public class ServiceResponse<T> implements RpcResponse<T> {

    private int code;
    private String msg;
    private T data;

    private ServiceResponse() {}

    public static <T> ServiceResponse<T> success() {
        return new ServiceResponse<>(0, "", null);
    }

    public static <T> ServiceResponse<T> success(T data) {
        return new ServiceResponse<>(0, "", data);
    }

    public T getCheckData() {
        checkRes();
        return this.getData();
    }

    public void checkRes() {
        if (this.getCode() != 0) {
            throw new RuntimeException(this.getMsg());
        }
    }

}
