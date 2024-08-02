package com.mrqinzh.common.entity;

import com.mrqinzh.commons.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysConfig extends BaseEntity {

    private String configKey;

    private String configValue;
}
