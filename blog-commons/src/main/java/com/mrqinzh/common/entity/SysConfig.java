package com.mrqinzh.common.entity;

import com.mrqinzh.commons.entity.BaseEntity;

public class SysConfig extends BaseEntity {

    private String configKey;

    private String configValue;

    public SysConfig() {
    }

    public SysConfig(String configKey, String configValue) {
        this.configKey = configKey;
        this.configValue = configValue;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
