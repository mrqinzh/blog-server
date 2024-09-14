package com.mrqinzh.user.service;

import com.mrqinzh.framework.common.domain.pojo.dto.PageDTO;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> test();

    Resp update(UserVO userVO);

    Resp add(UserVO userVO);

    Map<String, Object> info(String token);

    Resp list(PageDTO pageDTO);

    User getById(Integer id);

    User getByUsername(String username);

}
