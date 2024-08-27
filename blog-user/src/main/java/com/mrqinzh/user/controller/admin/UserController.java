package com.mrqinzh.user.controller.admin;

import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.web.controller.BaseController;
import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.vo.UserVO;
import com.mrqinzh.user.service.MenuService;
import com.mrqinzh.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController extends BaseController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;

    @Operation(summary = "获取所有用户信息")
    @GetMapping("list")
    public Resp list(PageDTO pageDTO) {
        return userService.list(pageDTO);
    }

    @Operation(summary = "根据id获取指定用户")
    @GetMapping("{id}")
    public Resp getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return DataResp.ok(user);
    }

    @Operation(summary = "添加一个用户")
    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp add(@RequestBody UserVO userVO) {
        return userService.add(userVO);
    }

    @Operation(summary = "修改用户信息")
    @PostMapping("update")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp update(@RequestBody UserVO userVO) {
        return userService.update(userVO);
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("info")
    public Resp info() {
//        SecurityUser securityUser = getCurrentUser();
//        if (securityUser == null) {
//            return new Resp(AppStatus.TOKEN_EXPIRED);
//        }
        User user = null;
        // 返回用户信息
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("name", user.getNickname());
        map.put("avatar", user.getAvatar());

        map.put("roles", user.getRoles());

        // Todo 暂时使用全部，用于前端调试
        map.put("menus", menuService.findAll());
//        map.put("menus", menuMapper.getByRoleId(user.getRole().getId()));
        return DataResp.ok(map);
    }

}
