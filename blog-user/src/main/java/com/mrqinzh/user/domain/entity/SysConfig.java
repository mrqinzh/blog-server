package com.mrqinzh.user.domain.entity;

import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SysConfig extends BaseEntity {

    private String configKey;

    private String configValue;

}
