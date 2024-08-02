package com.mrqinzh.webapp.controller;

import com.mrqinzh.apis.login.LoginLogService;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录日志接口")
@RestController
@RequestMapping("login-log")
public class LoginLogController {

//    @Autowired(required = false)
    private LoginLogService loginLogService;

    @ApiOperation(value = "登录日志列表")
    @GetMapping("list")
    public Resp list(PageVO pageVO) {
        return loginLogService.list(pageVO);
    }

}
