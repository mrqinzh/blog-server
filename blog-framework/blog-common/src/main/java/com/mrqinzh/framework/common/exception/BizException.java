package com.mrqinzh.framework.common.exception;

import cn.hutool.core.util.StrUtil;
import com.mrqinzh.framework.common.resp.Resp;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mrqinzh
 */
@Setter
@Getter
public class BizException extends RuntimeException {

    private Integer code;

    private String msg;

    public BizException(ErrorCode codeEntity) {
        this.code = codeEntity.getCode();
        this.msg = codeEntity.getMsg();
    }

    public BizException(ErrorCode codeEntity, String msg) {
        this.code = codeEntity.getCode();
        this.msg = StringUtils.isBlank(msg) ? codeEntity.getMsg() : msg;
    }

    public BizException(String msg) {
        this(ErrorCode.UNKNOWN_ERROR_CODE, msg);
    }

    public BizException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Resp sendExpResp() {
        return StrUtil.isBlank(this.msg) ?
                Resp.error(code == null ? ErrorCode.UNKNOWN_ERROR_CODE.getCode() : code, ErrorCode.UNKNOWN_ERROR_CODE.getMsg()) :
                Resp.error(code, msg);
    }

}
