package com.mrqinzh.webapp.controller;

import com.mrqinzh.apis.tag.TagService;
import com.mrqinzh.common.entity.Tag;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.enums.RoleType;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "标签接口")
@RestController
@RequestMapping("tag")
public class TagController {

    @DubboReference
    private TagService tagService;

    @ApiOperation(value = "根据id获取标签信息")
    @GetMapping("{id}")
    public Resp getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);
        return DataResp.ok(tag);
    }

    @ApiOperation(value = "添加标签")
    @PostMapping("add")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp add(@RequestBody Tag tag) {
        tagService.add(tag);
        return Resp.sendMsg(AppStatus.INSERT_SUCCESS);
    }

    @ApiOperation(value = "分页查询 tags")
    @GetMapping("page")
    public Resp page(PageVO pageVO) {
        return tagService.page(pageVO);
    }

    @ApiOperation(value = "查询所有标签，limit 20")
    @GetMapping("list")
    public Resp list() {
        List<Tag> tags = tagService.getByLimit();
        return DataResp.ok(tags);
    }

    @ApiOperation(value = "根据 tagId 删除标签")
    @DeleteMapping("{id}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp delete(@PathVariable Integer id) {
        return tagService.delete(id);
    }

    @ApiOperation(value = "修改标签信息")
    @PostMapping("update")
    public Resp update(@RequestBody Tag tag) {
        tagService.update(tag);
        return Resp.sendMsg(AppStatus.SUCCESS);
    }

}
