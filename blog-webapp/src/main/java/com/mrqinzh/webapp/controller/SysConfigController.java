package com.mrqinzh.webapp.controller;

import com.mrqinzh.apis.config.SysConfigService;
import com.mrqinzh.common.domain.entity.SysConfig;
import com.mrqinzh.common.domain.enums.ConfigKey;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.utils.BizAssert;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/sys/config")
@RestController
public class SysConfigController {

    @DubboReference
    private SysConfigService sysConfigService;

//    @AccessPermission(RoleType.SUPER_ADMIN)
    @PostMapping("setting")
    public Resp setting(@RequestBody SysConfig sysConfig) {
        BizAssert.isTrue(ConfigKey.validConfigKey(sysConfig.getConfigKey()), "config key is error");
        sysConfigService.setting(sysConfig);
        return Resp.success();
    }

//    @GetMapping("")
//    public Resp getPageConfig(PageVO<SysConfig> pageVO) {
//        return DataResp.ok(sysConfigService.page(pageVO));
//    }

    @GetMapping("byKeys")
    public Resp getConfigByKeys(String[] configKeys) {
        return DataResp.ok(sysConfigService.getByConfigKey(configKeys));
    }

}
