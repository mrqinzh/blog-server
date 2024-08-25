package com.mrqinzh.framework.common.domain.enums;

public enum AppStatus {

    LOGOUT(200, true, "退出成功了"),
    LOGIN_FAILURE(30001, true, "账号或密码错误了哦，请重新输入一下哦。。.>_>"),

    /**
     * 请求成功
     */
    SUCCESS(200, true, "success"),

    INSERT_SUCCESS(200, true, "添加成功"),
    UPDATE_SUCCESS(200, true,"更新成功"),
    DELETE_SUCCESS(200, true,"删除成功"),
    EMAIL_SEND_SUCCESS(200, true,"邮件发送成功"),


    BAD_PARAMETER_REQUEST(400, false, "参数校验失败"),
    USERNAME_PASSWORD_ERROR(4000 , false, "账号或密码错误！"),
    NO_PERMISSION(40002, false, "对不起，你的权限不足，请充值。。.>_>"),
    TOKEN_EXPIRED(40003, false, "会话失效了，请重新登录哦。。.>_>"),
    TOKEN_EXPIRE_NO_REDIRECT(40013, false, "会话失效了，删掉cookie就好了。。。-_-"),
    TOKEN_ILLEGAL(40004, false, "这个token好像有点不正常啊。。。-_-"),
    /**
     * 内部错误
     */
    UNKNOWN_SERVER_ERROR(500, false, "不好意思，服务端出现了未知的错误，赶快通知管理员修改BUG吧。。。-_-"),
    NULL_PRINTER_EXCEPTION(500, false, "服务器出现了空指针异常。。。"),
    SERVICE_ERROR(50000, false, "业务异常，可能是bug，也可能不是。。。-_-"),

    IMAGE_UPLOAD_ERROR(50001, false, "图片上传失败了"),
    INSERT_FAILED(50001, false, "添加失败"),
    UPDATE_FAILED(50001, false,"更新失败"),
    DELETE_FAILED(50001, false,"删除失败"),

    INTERNET_ERROR(50002, false, "网络好像出现了问题，稍后再试试吧。。。"),

    ;

    private int code;
    private Boolean success;
    private String msg;

    AppStatus(int code, Boolean success, String msg) {
        this.msg = msg;
        this.success = success;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Boolean getSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }
}