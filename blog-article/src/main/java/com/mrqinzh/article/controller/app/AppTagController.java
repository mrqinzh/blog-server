package com.mrqinzh.article.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.convert.TagConvert;
import com.mrqinzh.article.domain.dto.TagRespDTO;
import com.mrqinzh.article.service.TagService;
import com.mrqinzh.framework.common.domain.page.PageCondition;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
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
    public Resp getById(@PathVariable Long id) {
        TagRespDTO tag = tagService.getById(id);
        return DataResp.ok(TagConvert.INSTANCE.convert2RespVO(tag));
    }

    @Operation(summary = "分页查询 tags")
    @GetMapping("page")
    public Resp page(PageCondition pageReq) {
        Page<TagRespDTO> page = tagService.page(pageReq);
        return PageUtils.resp(page, TagConvert.INSTANCE::convert2RespVO);
    }

    @Operation(summary = "查询所有标签，limit 20")
    @GetMapping("list")
    public Resp list() {
        List<TagRespDTO> tags = tagService.getByLimit();
        return DataResp.ok(tags);
    }

}
