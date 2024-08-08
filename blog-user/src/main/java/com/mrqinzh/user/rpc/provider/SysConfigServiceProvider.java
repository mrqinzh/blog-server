package com.mrqinzh.user.rpc.provider;

import com.mrqinzh.apis.config.SysConfigService;
import com.mrqinzh.common.entity.SysConfig;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class SysConfigServiceProvider implements SysConfigService {

    @Resource
    private com.mrqinzh.user.service.SysConfigService sysConfigService;

    @Override
    public void setting(SysConfig sysConfig) {
        sysConfigService.setting(sysConfig);
    }

    @Override
    public List<SysConfig> getByConfigKey(String[] configKeys) {
        return sysConfigService.getByConfigKey(configKeys);
    }
}
