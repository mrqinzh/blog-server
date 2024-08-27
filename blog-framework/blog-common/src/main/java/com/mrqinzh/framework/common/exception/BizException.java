package com.mrqinzh.framework.common.exception;

import cn.hutool.core.util.StrUtil;
import com.mrqinzh.framework.common.domain.enums.AppStatus;
import com.mrqinzh.framework.common.resp.Resp;

/**
 * @author mrqinzh
 */
public class BizException extends RuntimeException {

    private AppStatus status;

    private Integer code;

    private String msg;

    @Deprecated
    public BizException(AppStatus status) {
        this.status = status;
    }

    public BizException(ErrorCode.CodeEntity codeEntity) {
        this.code = codeEntity.getCode();
        this.msg = codeEntity.getMsg();
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
        return StrUtil.isBlank(this.msg) ?
                Resp.error(code == null ? status.getCode() : code, status.getMsg()) :
                Resp.error(code, msg);
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
