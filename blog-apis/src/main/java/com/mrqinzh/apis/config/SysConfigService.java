package com.mrqinzh.apis.config;


import com.mrqinzh.common.entity.SysConfig;

import java.util.List;

public interface SysConfigService {

    void setting(SysConfig sysConfig);

    List<SysConfig> getByConfigKey(String[] configKeys);
}
