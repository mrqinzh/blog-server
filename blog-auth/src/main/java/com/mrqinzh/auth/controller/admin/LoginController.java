package com.mrqinzh.auth.controller.admin;

import com.mrqinzh.auth.service.LoginService;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping("log/page")
    public Resp loginLog(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return PageUtils.resp(loginService.logPage(pageNum, pageSize));
    }

}
