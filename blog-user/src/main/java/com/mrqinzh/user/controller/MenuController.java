package com.mrqinzh.user.controller;

import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.web.controller.BaseController;
import com.mrqinzh.user.domain.entity.Menu;
import com.mrqinzh.user.domain.vo.MenuVO;
import com.mrqinzh.user.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "菜单接口")
@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {

    @Resource
    private MenuService menuService;

    @Operation(summary = "获取所有菜单信息")
    @GetMapping("list")
    public Resp findList() {
        List<Menu> menus = menuService.findAll();
        return DataResp.ok(menus);
    }

    @Operation(summary = "分页获取菜单信息")
    @GetMapping("page")
    public Resp findPage(PageDTO pageDTO) {
        List<Menu> menus = menuService.findPage(pageDTO);
        return DataResp.ok(menus);
    }

    @Operation(summary = "根据id获取菜单信息")
    @GetMapping("{id}")
    public Resp getById(@PathVariable Integer id) {
        Menu menu = menuService.findById(id);
        return DataResp.ok(menu);
    }

    @Operation(summary = "添加菜单")
    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp add(@RequestBody @Valid MenuVO menuVO) {
        menuService.add(menuVO);
        return Resp.success();
    }

    @Operation(summary = "修改菜单")
    @PostMapping("update")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp update(@RequestBody @Valid MenuVO menuVO) {
        menuService.update(menuVO);
        return Resp.success();
    }

    @Operation(summary = "根据id删除菜单")
    @DeleteMapping("{id}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp delete(@PathVariable Integer id) {
        menuService.delete(id);
        return Resp.success();
    }

}
