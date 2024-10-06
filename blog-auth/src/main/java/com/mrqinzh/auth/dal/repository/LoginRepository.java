package com.mrqinzh.auth.dal.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.auth.dal.mapper.LoginMapper;
import com.mrqinzh.auth.domain.bo.LoginLogBO;
import com.mrqinzh.auth.domain.convert.LoginConvert;
import com.mrqinzh.auth.domain.entity.LoginLog;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LoginRepository {

    @Resource
    private LoginMapper mapper;

    public Page<LoginLogBO> logPage(Page<LoginLogBO> pageReq) {
        Page<LoginLog> page = new Page<>(pageReq.getCurrent(), pageReq.getSize());
        page = mapper.selectPage(page, null);
        return PageUtils.convert(page, LoginConvert.INSTANCE::convert2LogBO);
    }

    public void add(LoginLog loginLog) {
        mapper.insert(loginLog);
    }

    public void delete(Long id) {
        mapper.delete(new LambdaQueryWrapper<LoginLog>().eq(LoginLog::getId, id));
    }

    public List<LoginLogBO> getByUserId(Long userId) {
        return mapper.selectList(new LambdaQueryWrapper<LoginLog>().eq(LoginLog::getUserId, userId)).stream().map(LoginConvert.INSTANCE::convert2LogBO).toList();
    }


}
