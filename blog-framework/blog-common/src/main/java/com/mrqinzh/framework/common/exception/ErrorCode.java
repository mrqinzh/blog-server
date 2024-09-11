package com.mrqinzh.framework.common.exception;

public interface ErrorCode {

    CodeEntity SUCCESS = new CodeEntity(0, "请求成功");
    CodeEntity UNKNOWN_ERROR_CODE = new CodeEntity(60000, "未标注code码的异常");

    CodeEntity USERNAME_PASSWORD_ERROR = new CodeEntity(30100, "账号或密码错误！");
    CodeEntity ACCOUNT_LOCKED = new CodeEntity(30101, "账户被锁定了");

    CodeEntity NO_PERMISSION = new CodeEntity(40002, "对不起，你的权限不足，请充值。。.>_>");
    CodeEntity TOKEN_EXPIRED = new CodeEntity(40003, "会话失效了，请重新登录哦。。.>_>");
    CodeEntity TOKEN_EXPIRE_NO_REDIRECT = new CodeEntity(40013, "会话失效了，删掉cookie就好了。。。-_-");
    CodeEntity TOKEN_ILLEGAL = new CodeEntity(40004, "这个token好像有点不正常啊。。。-_-");

    CodeEntity BAD_PARAMETER = new CodeEntity(400, "参数校验失败");

    CodeEntity UNKNOWN_SERVER_ERROR = new CodeEntity(500, "不好意思，服务端出现了未知的错误，赶快通知管理员修改BUG吧。。。-_-");
    CodeEntity NULL_PRINTER_EXCEPTION = new CodeEntity(500, "服务器出现了空指针异常。。。");
    CodeEntity SERVICE_ERROR = new CodeEntity(50000, "业务异常，可能是bug，也可能不是。。。-_-");

    int getCode();
    String getMsg();

    class CodeEntity implements ErrorCode {

        private final int code;
        private final String msg;

        public CodeEntity(int code, String message) {
            this.code = code;
            this.msg = message;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }
}
