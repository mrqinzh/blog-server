package com.mrqinzh.user.api;

import java.util.List;

public interface SysConfigApiService {

    void setting(SysConfig sysConfig);

    List<SysConfig> getByConfigKey(String[] configKeys);
}
