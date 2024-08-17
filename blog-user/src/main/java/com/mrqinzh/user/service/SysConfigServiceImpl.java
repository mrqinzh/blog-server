package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mrqinzh.common.domain.entity.SysConfig;
import com.mrqinzh.user.mapper.SysConfigMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysConfigServiceImpl extends ServiceImpl<SysConfigMapper, SysConfig> implements SysConfigService {

    @Resource
    private SysConfigMapper sysConfigMapper;

    @Override
    public void setting(SysConfig sysConfig) {
        sysConfigMapper.updateById(sysConfig);
    }

    @Override
    public List<SysConfig> getByConfigKey(String[] configKeys) {
        LambdaQueryWrapper<SysConfig> queryWrapper = new LambdaQueryWrapper<SysConfig>()
                .in(SysConfig::getConfigKey, configKeys);
        return sysConfigMapper.selectList(queryWrapper);
    }
}
