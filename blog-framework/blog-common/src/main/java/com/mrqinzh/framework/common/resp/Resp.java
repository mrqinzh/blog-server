package com.mrqinzh.framework.common.resp;

import com.mrqinzh.framework.common.exception.ErrorCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 统一返回数据类型
 */
@Setter
@Getter
public class Resp implements Serializable {

    public static final Resp SUCCESS = new Resp(ErrorCode.SUCCESS);
    public static final Resp NULL = new Resp();

    private int code;
    private boolean success;
    private String msg;

    protected Resp() {
    }

    private Resp(int code, boolean success, String msg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
    }

    protected Resp(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.success = Objects.equals(ErrorCode.SUCCESS.getCode(), errorCode.getCode());
        this.msg = errorCode.getMsg();
    }

    public static Resp success() {
        return SUCCESS;
    }

    public static Resp success(String msg) {
        return new Resp(SUCCESS.getCode(), true, msg);
    }

    public static Resp error(String msg) {
        return error(ErrorCode.UNKNOWN_ERROR_CODE.getCode(), msg);
    }

    public static Resp error(ErrorCode errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static Resp error(Integer code, String msg) {
        Resp resp = new Resp();
        resp.setCode(code);
        resp.setSuccess(false);
        resp.setMsg(msg);
        return resp;
    }

}