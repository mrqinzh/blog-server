package com.mrqinzh.framework.common.resp;

import com.mrqinzh.framework.common.domain.enums.AppStatus;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.exception.ErrorCodeConstants;
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

    private static final long serialVersionUID = 1L;

    public static final Resp SUCCESS = new Resp(ErrorCodeConstants.SUCCESS);

    private Integer code;
    private Boolean success;
    private String msg;

    public Resp() {
    }

    public Resp(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.success = Objects.equals(ErrorCodeConstants.SUCCESS.getCode(), errorCode.getCode());
        this.msg = errorCode.getMsg();
    }

    public Resp(AppStatus status) {
        this.code = status.getCode();
        this.success = status.getSuccess();
        this.msg = status.getMsg();
    }

    public static Resp success() {
        return SUCCESS;
    }

    @Deprecated
    public static Resp sendMsg(AppStatus status) {
        Resp resp = new Resp();
        resp.setCode(status.getCode());
        resp.setSuccess(status.getSuccess());
        resp.setMsg(status.getMsg());
        return resp;
    }

    public static Resp error(Integer code, String msg) {
        Resp resp = new Resp();
        resp.setCode(code);
        resp.setSuccess(false);
        resp.setMsg(msg);
        return resp;
    }

    public static Resp error(ErrorCode errorCode) {
        Resp resp = new Resp();
        resp.setCode(errorCode.getCode());
        resp.setSuccess(false);
        resp.setMsg(errorCode.getMsg());
        return resp;
    }


}