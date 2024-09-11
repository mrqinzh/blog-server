package com.mrqinzh.user.dal.repo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mrqinzh.framework.common.constant.CacheKeyConstant;
import com.mrqinzh.framework.redis.utils.RedisUtil;
import com.mrqinzh.user.dal.mapper.SysConfigMapper;
import com.mrqinzh.user.domain.entity.SysConfig;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SysConfigRepository {

    @Resource
    private SysConfigMapper sysConfigMapper;

    public void cacheConfigs() {
        getAllConfigs();
    }

    public List<SysConfig> getAllConfigs() {
        List<SysConfig> configs = RedisUtil.get(getCacheKey());
        if (configs != null) {
            return configs;
        }
        configs = sysConfigMapper.selectList(new QueryWrapper<>());
        RedisUtil.set(getCacheKey(), configs, CacheKeyConstant.SysConfig.EXPIRE_TIME);
        return configs;
    }

    private String getCacheKey() {
        return CacheKeyConstant.SysConfig.CACHE_KEY;
    }
}
