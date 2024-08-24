package com.mrqinzh.user.service;

import com.mrqinzh.user.domain.entity.SysConfig;

import java.util.List;

public interface SysConfigService {

    void setting(SysConfig sysConfig);

    List<SysConfig> getByConfigKey(String[] configKeys);

}
