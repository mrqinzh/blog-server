package com.mrqinzh.article.controller.admin;

import com.mrqinzh.apis.tag.TagService;
import com.mrqinzh.common.domain.entity.Tag;
import com.mrqinzh.common.domain.enums.AppStatus;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.dto.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "标签接口")
@RestController
@RequestMapping("tag")
public class TagController {

    @DubboReference
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
