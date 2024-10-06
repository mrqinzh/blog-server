package com.mrqinzh.user.controller.admin;

import com.mrqinzh.user.domain.enums.ConfigKey;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.web.controller.BaseController;
import com.mrqinzh.user.domain.entity.SysConfig;
import com.mrqinzh.user.service.SysConfigService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/sys/config")
@RestController
public class SysConfigController extends BaseController {

    @Resource
    private SysConfigService sysConfigService;

//    @AccessPermission(RoleType.SUPER_ADMIN)
    @PostMapping("setting")
    public Resp setting(@RequestBody SysConfig sysConfig) {
        if (!ConfigKey.validConfigKey(sysConfig.getConfigKey())) {
            throw new BizException("config key is error");
        }
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
