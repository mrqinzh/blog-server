package com.mrqinzh.webapp.service;

import com.mrqinzh.common.entity.User;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.user.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> test();

    Resp update(UserVO userVO);

    Resp add(UserVO userVO);

    Map<String, Object> info(String token);

    Resp list(PageVO pageVO);

    User getById(Integer id);

}
