package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrqinzh.user.dal.repo.SysConfigRepository;
import com.mrqinzh.user.domain.entity.SysConfig;
import com.mrqinzh.user.dal.mapper.SysConfigMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysConfigServiceImpl implements SysConfigService, InitializingBean {

    @Resource
    private SysConfigMapper sysConfigMapper;
    @Resource
    private SysConfigRepository sysConfigRepository;

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

    @Override
    public void afterPropertiesSet() throws Exception {
        sysConfigRepository.cacheConfigs();
    }
}
