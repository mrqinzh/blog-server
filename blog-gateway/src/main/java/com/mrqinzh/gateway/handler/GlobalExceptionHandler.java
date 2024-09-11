package com.mrqinzh.gateway.handler;

import cn.hutool.core.util.StrUtil;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.Resp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author mrqinzh
 * @Description 全局异常处理器 gateway 模块暂未引入 blog-framework-web
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理业务发生的异常
     * @param e
     */
    @ExceptionHandler(value = BizException.class)
    public Resp bizExceptionHandler(BizException e) {
        log.error(e.getMessage(), e);
        return e.sendExpResp();
    }

    /**
     * 处理参数 valid 校验异常
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Resp handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrorList) {
            stringBuilder.append(error.getField()).append("(").append(error.getDefaultMessage()).append("); ");
        }
        String errMsg = StrUtil.removeSuffix(stringBuilder.toString(), ";");
        return Resp.error(ErrorCode.BAD_PARAMETER.getCode(), errMsg);
    }

    /**
     * 处理空指针异常
     * @param e
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Resp exceptionHandler(NullPointerException e) {
        log.error(e.getMessage(), e);
        return Resp.error(ErrorCode.NULL_PRINTER_EXCEPTION);
    }

    /**
     * 处理其他异常情况
     */
    @ExceptionHandler(value = Exception.class)
    public Resp exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return Resp.error(ErrorCode.UNKNOWN_SERVER_ERROR);
    }

}
