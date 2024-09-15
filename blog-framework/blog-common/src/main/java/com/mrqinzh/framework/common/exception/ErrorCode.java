package com.mrqinzh.framework.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // 成功相关
    SUCCESS(0, "请求成功"),
    UNKNOWN_ERROR_CODE(60000, "未标注code码的异常"),

    // 用户登录相关
    USERNAME_PASSWORD_ERROR(30100, "账号或密码错误！"),
    ACCOUNT_LOCKED(30101, "账户被锁定了"),

    // 权限相关
    NO_PERMISSION(40002, "对不起，你的权限不足，请充值。。.>_>"),
    TOKEN_EXPIRED(40003, "会话失效了，请重新登录哦。。.>_>"),
    TOKEN_EXPIRE_NO_REDIRECT(40013, "会话失效了，删掉cookie就好了。。。-_-"),
    TOKEN_ILLEGAL(40004, "这个token好像有点不正常啊。。。-_-"),

    // 参数相关
    BAD_PARAMETER(400, "参数校验失败"),

    // 服务器相关
    UNKNOWN_SERVER_ERROR(500, "不好意思，服务端出现了未知的错误，赶快通知管理员修改BUG吧。。。-_-"),
    NULL_PRINTER_EXCEPTION(500, "服务器出现了空指针异常。。。"),
    SERVICE_ERROR(50000, "业务异常，可能是bug，也可能不是。。。-_-");

    // 获取code
    // 属性
    private final int code;
    // 获取msg
    private final String msg;

    // 构造函数
    ErrorCode(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    // 覆盖toString方法（可选）
    @Override
    public String toString() {
        return String.format("ErrorCode{code=%d, msg='%s'}", code, msg);
    }
}
