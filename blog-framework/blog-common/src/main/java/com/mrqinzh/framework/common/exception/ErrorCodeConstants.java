package com.mrqinzh.framework.common.exception;

public interface ErrorCodeConstants {

    ErrorCode SUCCESS = new ErrorCode(0, "请求成功");

    ErrorCode USERNAME_PASSWORD_ERROR = new ErrorCode(30000, "账号或密码错误！");
    ErrorCode ACCOUNT_LOCKED = new ErrorCode(30002, "账户被锁定了");

    ErrorCode BAD_PARAMETER = new ErrorCode(400, "参数校验失败");
}
