package com.mrqinzh.framework.common.exception;

import com.mrqinzh.framework.common.domain.enums.AppStatus;
import lombok.Data;

@Data
public class ErrorCode {

    private final Integer code;
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    // todo 暂时保留，适配 appStatus ，后续删除
    public ErrorCode(AppStatus appStatus) {
        this.code = appStatus.getCode();
        this.msg = appStatus.getMsg();
    }

}
