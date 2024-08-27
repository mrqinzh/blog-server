package com.mrqinzh.user.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.web.controller.BaseController;
import com.mrqinzh.user.domain.entity.Role;
import com.mrqinzh.user.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "角色接口")
@RestController
@RequestMapping("role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "获取所有角色列表")
    @GetMapping("list")
    public Resp findAll() {
        List<Role> roles = roleService.findAll();
        return DataResp.ok(roles);
    }

    @Operation(summary = "分页获取所有角色列表")
    @GetMapping("page")
    public Resp findPage(PageDTO pageDTO) {
        Page<Role> roles = roleService.findPage(pageDTO);
        return DataResp.ok(roles);
    }

    @Operation(summary = "根据id获取指定角色信息")
    @GetMapping("{id}")
    public Resp findById(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return DataResp.ok(role);
    }

    @Operation(summary = "添加角色")
    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp add(@RequestBody Role role) {
        roleService.add(role);
        return Resp.success();
    }

    @Operation(summary = "根据id更新角色信息")
    @PostMapping("update")
    public Resp update(@RequestBody Role role) {
        roleService.update(role);
        return Resp.success();
    }

    @Operation(summary = "根据id删除角色")
    @DeleteMapping("{id}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp delete(@PathVariable Long id) {
        roleService.delete(id);
        return Resp.success();
    }




}
