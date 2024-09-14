package com.mrqinzh.article.controller.app;

import com.mrqinzh.article.domain.entity.Tag;
import com.mrqinzh.article.service.TagService;
import com.mrqinzh.framework.common.domain.pojo.page.BasePageReq;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tag")
public class AppTagController {

    @Resource
    private TagService tagService;

    @Operation(summary = "根据id获取标签信息")
    @GetMapping("{id}")
    public Resp getById(@PathVariable Integer id) {
        Tag tag = tagService.getById(id);
        return DataResp.ok(tag);
    }

    @Operation(summary = "分页查询 tags")
    @GetMapping("page")
    public Resp page(BasePageReq pageReq) {
        return tagService.page(pageReq);
    }

    @Operation(summary = "查询所有标签，limit 20")
    @GetMapping("list")
    public Resp list() {
        List<Tag> tags = tagService.getByLimit();
        return DataResp.ok(tags);
    }

}
