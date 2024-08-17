package com.mrqinzh.webapp.service;

import com.mrqinzh.common.domain.entity.User;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.vo.user.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> test();

    Resp update(UserVO userVO);

    Resp add(UserVO userVO);

    Map<String, Object> info(String token);

    Resp list(PageDTO pageDTO);

    User getById(Integer id);

}
