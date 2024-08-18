package com.mrqinzh.common.exception;

import com.mrqinzh.common.domain.enums.AppStatus;
import com.mrqinzh.common.resp.Resp;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mrqinzh
 */
public class BizException extends RuntimeException {

    private AppStatus status;

    private Integer code;

    private String msg;

    public BizException(AppStatus status) {
        this.status = status;
    }

    public BizException(String msg) {
        this.status = AppStatus.SERVICE_ERROR;
        this.msg = msg;
    }

    public BizException(AppStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public BizException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Resp sendExceptionMsg() {
        return StringUtils.isBlank(this.msg) ?
                Resp.sendErrorMsg(code == null ? status.getCode() : code, status.getMsg()) :
                Resp.sendErrorMsg(code, msg);
    }

    public AppStatus getStatus() {
        return status;
    }

    public void setStatus(AppStatus status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
