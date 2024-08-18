package com.mrqinzh.webapp.controller;

import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.framework.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("test")
@RestController
public class TestController {


    @GetMapping
    public Resp testRedisKeyExpire() {
        RedisUtil.set("test_expire", 1, 5);
        return Resp.success();
    }


}
