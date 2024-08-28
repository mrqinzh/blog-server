package com.mrqinzh.user.controller.app;

import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.security.LoginUser;
import com.mrqinzh.user.controller.BaseUserController;
import com.mrqinzh.user.domain.convert.MenuConvert;
import com.mrqinzh.user.domain.vo.AppUserInfoVO;
import com.mrqinzh.user.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class AppUserController extends BaseUserController {

    @Resource
    private MenuService menuService;

    @Operation(summary = "获取用户信息")
    @GetMapping("info")
    public Resp info() {
        LoginUser user = getUser();
        if (user == null) {
            return Resp.error(ErrorCode.TOKEN_EXPIRED);
        }
        AppUserInfoVO vo = new AppUserInfoVO();
        vo.setUserId(user.getUserId());
        vo.setName(user.getNickname());
        vo.setAvatar(user.getAvatar());
        vo.setRoles(user.getRoles());

        // Todo 暂时使用全部，用于前端调试
        vo.setMenuVOS(menuService.findAll().stream().map(MenuConvert.INSTANCE::convert).collect(Collectors.toList()));
//        vo.setMenuVOS("menus", menuMapper.getByRoleId(user.getRole().getId()));
        return DataResp.ok(vo);
    }

}
