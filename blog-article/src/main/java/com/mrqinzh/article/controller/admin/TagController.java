package com.mrqinzh.article.controller.admin;

import com.mrqinzh.article.domain.entity.Tag;
import com.mrqinzh.article.service.TagService;
import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.domain.enums.AppStatus;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@io.swagger.v3.oas.annotations.tags.Tag(name = "标签接口")
@RestController
@RequestMapping("tag")
public class TagController extends BaseController {

    @Resource
    private TagService tagService;

    @Operation(summary = "根据id获取标签信息")
    @GetMapping("{id}")
    public Resp getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);
        return DataResp.ok(tag);
    }

    @Operation(summary = "添加标签")
    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp add(@RequestBody Tag tag) {
        tagService.add(tag);
        return Resp.sendMsg(AppStatus.INSERT_SUCCESS);
    }

    @Operation(summary = "分页查询 tags")
    @GetMapping("page")
    public Resp page(PageDTO pageDTO) {
        return tagService.page(pageDTO);
    }

    @Operation(summary = "查询所有标签，limit 20")
    @GetMapping("list")
    public Resp list() {
        List<Tag> tags = tagService.getByLimit();
        return DataResp.ok(tags);
    }

    @Operation(summary = "根据 tagId 删除标签")
    @DeleteMapping("{id}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp delete(@PathVariable Integer id) {
        return tagService.delete(id);
    }

    @Operation(summary = "修改标签信息")
    @PostMapping("update")
    public Resp update(@RequestBody Tag tag) {
        tagService.update(tag);
        return Resp.sendMsg(AppStatus.SUCCESS);
    }

}
