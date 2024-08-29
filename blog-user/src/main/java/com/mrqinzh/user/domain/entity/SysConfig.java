package com.mrqinzh.user.domain.entity;

import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SysConfig extends BaseEntity {

    private String configKey;

    private String configValue;

    public SysConfig() {
    }

    public SysConfig(String configKey, String configValue) {
        this.configKey = configKey;
        this.configValue = configValue;
    }

}
