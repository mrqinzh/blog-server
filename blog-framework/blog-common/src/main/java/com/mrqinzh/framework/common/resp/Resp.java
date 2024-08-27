package com.mrqinzh.framework.common.resp;

import com.mrqinzh.framework.common.domain.enums.AppStatus;
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

    private static final long serialVersionUID = 1L;

    public static final Resp SUCCESS = new Resp(ErrorCode.SUCCESS);

    private Integer code;
    private Boolean success;
    private String msg;

    public Resp() {
    }

    public Resp(ErrorCode.CodeEntity codeEntity) {
        this.code = codeEntity.getCode();
        this.success = Objects.equals(ErrorCode.SUCCESS.getCode(), codeEntity.getCode());
        this.msg = codeEntity.getMsg();
    }

    public Resp(AppStatus status) {
        this.code = status.getCode();
        this.success = status.getSuccess();
        this.msg = status.getMsg();
    }

    public static Resp success(String msg) {
        Resp resp = new Resp(ErrorCode.SUCCESS);
        resp.setMsg(msg);
        return resp;
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

    public static Resp error(ErrorCode.CodeEntity codeEntity) {
        Resp resp = new Resp();
        resp.setCode(codeEntity.getCode());
        resp.setSuccess(false);
        resp.setMsg(codeEntity.getMsg());
        return resp;
    }


}