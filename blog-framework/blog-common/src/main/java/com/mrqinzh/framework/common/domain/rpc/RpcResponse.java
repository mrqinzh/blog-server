package com.mrqinzh.framework.common.domain.rpc;

import java.io.Serializable;

public interface RpcResponse<T> extends Serializable {

    int getCode();

    String getMsg();

    T getData();

}
