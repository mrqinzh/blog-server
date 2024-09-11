package com.mrqinzh.user.controller.app;

import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.user.controller.BaseUserController;
import com.mrqinzh.user.service.SysConfigService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/config")
public class AppSysConfigController extends BaseUserController {

    @Resource
    private SysConfigService sysConfigService;

    @GetMapping("byKeys")
    public Resp getConfigByKeys(@RequestParam("configKeys") String[] configKeys) {
        return DataResp.ok(sysConfigService.getByConfigKey(configKeys));
    }

}
