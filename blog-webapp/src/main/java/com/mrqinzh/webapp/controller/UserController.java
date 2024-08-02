package com.mrqinzh.webapp.controller;

import com.mrqinzh.apis.menu.MenuService;
import com.mrqinzh.common.entity.User;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.user.UserVO;
import com.mrqinzh.webapp.secure.authentication.context.AuthenticationContextUtils;
import com.mrqinzh.webapp.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userServiceProxy;

    @RequestMapping("user")
    public List<User> testDubbo() {
        return userServiceProxy.test();
    }

    @ApiOperation(value = "获取所有用户信息")
    @GetMapping("list")
    public Resp list(PageVO pageVO) {
        return userServiceProxy.list(pageVO);
    }

    @ApiOperation(value = "根据id获取指定用户")
    @GetMapping("{id}")
    public Resp getById(@PathVariable Integer id) {
        User user = userServiceProxy.getById(id);
        return DataResp.ok(user);
    }

    @ApiOperation(value = "添加一个用户")
    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp add(@RequestBody UserVO userVO) {
        return userServiceProxy.add(userVO);
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("update")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp update(@RequestBody UserVO userVO) {
        return userServiceProxy.update(userVO);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("info")
    public Resp info() {
        User user = AuthenticationContextUtils.getUser();
        if (user == null) {
            return new Resp(AppStatus.TOKEN_EXPIRED);
        }
        // 返回用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("name", user.getUserNickname());
        map.put("avatar", user.getUserAvatar());

        map.put("roles", user.getRoles());

        // Todo 暂时使用全部，用于前端调试
        map.put("menus", menuService.findAll());
//        map.put("menus", menuMapper.getByRoleId(user.getRole().getId()));
        return DataResp.ok(map);
    }

}
