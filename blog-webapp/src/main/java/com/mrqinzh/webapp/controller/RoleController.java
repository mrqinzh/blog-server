package com.mrqinzh.webapp.controller;

import com.mrqinzh.apis.role.RoleService;
import com.mrqinzh.common.domain.entity.Role;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.dto.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequestMapping("role")
public class RoleController {

//    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取所有角色列表")
    @GetMapping("list")
    public Resp findAll() {
        List<Role> roles = roleService.findAll();
        return DataResp.ok(roles);
    }

    @ApiOperation(value = "分页获取所有角色列表")
    @GetMapping("page")
    public Resp findPage(PageDTO pageDTO) {
        List<Role> roles = roleService.findPage(pageDTO);
        return DataResp.ok(roles);
    }

    @ApiOperation(value = "根据id获取指定角色信息")
    @GetMapping("{id}")
    public Resp findById(@PathVariable Integer id) {
        Role role = roleService.getById(id);
        return DataResp.ok(role);
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp add(@RequestBody Role role) {
        roleService.add(role);
        return Resp.success();
    }

    @ApiOperation(value = "根据id更新角色信息")
    @PostMapping("update")
    public Resp update(@RequestBody Role role) {
        roleService.update(role);
        return Resp.success();
    }

    @ApiOperation(value = "根据id删除角色")
    @DeleteMapping("{id}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp delete(@PathVariable Integer id) {
        roleService.delete(id);
        return Resp.success();
    }




}
