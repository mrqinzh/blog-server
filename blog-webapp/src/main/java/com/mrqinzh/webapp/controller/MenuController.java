package com.mrqinzh.webapp.controller;

import com.mrqinzh.apis.menu.MenuService;
import com.mrqinzh.common.domain.entity.Menu;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.vo.menu.MenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "菜单接口")
@RestController
@RequestMapping("menu")
public class MenuController {

//    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "获取所有菜单信息")
    @GetMapping("list")
    public Resp findList() {
        List<Menu> menus = menuService.findAll();
        return DataResp.ok(menus);
    }

    @ApiOperation(value = "分页获取菜单信息")
    @GetMapping("page")
    public Resp findPage(PageDTO pageDTO) {
        List<Menu> menus = menuService.findPage(pageDTO);
        return DataResp.ok(menus);
    }

    @ApiOperation(value = "根据id获取菜单信息")
    @GetMapping("{id}")
    public Resp getById(@PathVariable Integer id) {
        Menu menu = menuService.findById(id);
        return DataResp.ok(menu);
    }

    @ApiOperation(value = "添加菜单")
    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp add(@RequestBody @Valid MenuVO menuVO) {
        menuService.add(menuVO);
        return Resp.success();
    }

    @ApiOperation(value = "修改菜单")
    @PostMapping("update")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp update(@RequestBody @Valid MenuVO menuVO) {
        menuService.update(menuVO);
        return Resp.success();
    }

    @ApiOperation(value = "根据id删除菜单")
    @DeleteMapping("{id}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp delete(@PathVariable Integer id) {
        menuService.delete(id);
        return Resp.success();
    }

}
