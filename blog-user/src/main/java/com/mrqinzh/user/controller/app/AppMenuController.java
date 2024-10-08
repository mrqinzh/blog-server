package com.mrqinzh.user.controller.app;

import com.mrqinzh.framework.common.resp.CollectionDataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.user.domain.convert.MenuConvert;
import com.mrqinzh.user.domain.entity.Menu;
import com.mrqinzh.user.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
public class AppMenuController {

    @Resource
    private MenuService menuService;

    @Operation(summary = "获取所有菜单信息")
    @GetMapping("list")
    public Resp findList() {
        List<Menu> menus = menuService.findAll();
        return CollectionDataResp.ok(menus, MenuConvert.INSTANCE::convert);
    }

}
