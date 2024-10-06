package com.mrqinzh.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.auth.dal.repository.LoginLogRepository;
import com.mrqinzh.auth.domain.bo.LoginLogBO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LoginLogService {

    @Resource
    private LoginLogRepository repository;

    public Page<LoginLogBO> logPage(int pageNum, int pageSize) {
        Page<LoginLogBO> page = new Page<>(pageNum, pageSize);
        return repository.logPage(page);
    }

    public void create(LoginLogBO loginLogBO) {
        repository.add(loginLogBO);
    }
}
