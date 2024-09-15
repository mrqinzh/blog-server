package com.mrqinzh.user.dal.repo;

import com.mrqinzh.user.dal.mapper.MenuMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class MenuRepository {

    @Resource
    private MenuMapper menuMapper;

}
