package com.mrqinzh.framework.common.resp;

import com.mrqinzh.framework.common.domain.enums.AppStatus;

import java.io.Serializable;

/**
 * 统一返回数据类型
 */
public class Resp implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Resp SUCCESS = new Resp(AppStatus.SUCCESS);

    private Integer code;
    private Boolean success;
    private String msg;

    public Resp() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Resp(AppStatus status) {
        this.code = status.getCode();
        this.success = status.getSuccess();
        this.msg = status.getMsg();
    }

    public static Resp success() {
        return Resp.sendMsg(AppStatus.SUCCESS);
    }

    public static Resp sendSuccessMsg(String msg) {
        return sendMsg(AppStatus.SUCCESS, msg);
    }

    public static Resp sendMsg(AppStatus status, String msg) {
        Resp resp = new Resp();
        resp.setCode(status.getCode());
        resp.setSuccess(status.getSuccess());

        resp.setMsg(msg);

        return resp;
    }

    public static Resp sendMsg(AppStatus status) {
        Resp resp = new Resp();
        resp.setCode(status.getCode());
        resp.setSuccess(status.getSuccess());
        resp.setMsg(status.getMsg());
        return resp;
    }

    public static Resp sendErrorMsg(Integer code, String msg) {
        Resp resp = new Resp();
        resp.setCode(code);
        resp.setSuccess(false);
        resp.setMsg(msg);
        return resp;
    }


}