package com.mrqinzh.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.domain.page.PageCondition;
import com.mrqinzh.user.domain.dto.UserStatisticsDTO;
import com.mrqinzh.user.domain.dto.UserRespDTO;
import com.mrqinzh.user.domain.vo.UserVO;

import java.util.Map;

public interface UserService {

    void update(UserVO userVO);

    void add(UserVO userVO);

    Map<String, Object> info(String token);

    Page<UserRespDTO> page(PageCondition pageReq);

    UserRespDTO getById(Long id);

    UserRespDTO getByUsername(String username);

    UserStatisticsDTO statistics();

    long count();
}
