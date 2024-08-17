package com.mrqinzh.user.rpc.provider;

import com.mrqinzh.apis.menu.MenuService;
import com.mrqinzh.apis.user.UserService;
import com.mrqinzh.common.entity.User;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.exception.BizException;
import com.mrqinzh.common.resp.PageResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.user.UserVO;
import com.mrqinzh.common.vo.PageVO;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@DubboService
public class UserServiceProvider implements UserService {

    @Resource
    private com.mrqinzh.user.service.UserService userService;

    @Override
    public List<User> test() {
        return userService.test();
    }

    @Override
    public Resp update(UserVO userVO) {
        return userService.update(userVO);
    }

    @Override
    public Resp add(UserVO userVO) {
        return userService.add(userVO);
    }

    @Override
    public Map<String, Object> info(String token) {
        return userService.info(token);
    }

    @Override
    public Resp list(PageVO pageVO) {
        return userService.list(pageVO);
    }

    @Override
    public User getById(Integer id) {
        return userService.getById(id);
    }
}
