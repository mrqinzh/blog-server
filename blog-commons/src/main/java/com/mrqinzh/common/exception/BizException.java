package com.mrqinzh.common.exception;

import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.resp.Resp;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mrqinzh
 */
@Data
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

}
