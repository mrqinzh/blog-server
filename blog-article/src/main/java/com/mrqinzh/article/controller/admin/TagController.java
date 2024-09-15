package com.mrqinzh.article.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.convert.TagConvert;
import com.mrqinzh.article.domain.dto.TagRespDTO;
import com.mrqinzh.article.domain.entity.Tag;
import com.mrqinzh.article.domain.vo.TagReqVO;
import com.mrqinzh.article.domain.vo.TagRespVO;
import com.mrqinzh.article.service.TagService;
import com.mrqinzh.framework.common.domain.pojo.page.PageCondition;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.PageResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.web.controller.BaseController;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@io.swagger.v3.oas.annotations.tags.Tag(name = "标签接口")
@RestController
@RequestMapping("tag")
public class TagController extends BaseController {

    @Resource
    private TagService tagService;

    @Operation(summary = "根据id获取标签信息")
    @GetMapping("{id}")
    public Resp getById(@PathVariable("id") Long id) {
        TagRespDTO tag = tagService.getById(id);
        return DataResp.ok(TagConvert.INSTANCE.convert2RespVO(tag));
    }

    @Operation(summary = "添加标签")
    @PostMapping("add")
    public Resp add(@RequestBody TagReqVO tag) {
        tagService.add(TagConvert.INSTANCE.convert2ReqDTO(tag));
        return Resp.success();
    }

    @Operation(summary = "分页查询 tags")
    @GetMapping("page")
    public PageResp<TagRespVO> page(PageCondition pageReq) {
        Page<TagRespDTO> page = tagService.page(pageReq);
        return PageUtils.resp(page, TagConvert.INSTANCE::convert2RespVO);
    }

    @Operation(summary = "查询所有标签，limit 20")
    @GetMapping("list")
    public Resp list() {
        List<TagRespDTO> tags = tagService.getByLimit();
        return DataResp.ok(tags);
    }

    @Operation(summary = "根据 tagId 删除标签")
    @DeleteMapping("{id}")
    public Resp delete(@PathVariable("id") Long id) {
        tagService.delete(id);
        return Resp.success();
    }

    @Operation(summary = "修改标签信息")
    @PostMapping("update")
    public Resp update(@RequestBody TagReqVO tag) {
        if (Objects.isNull(tag.getId())) {
            return Resp.errorParam();
        }
        tagService.update(TagConvert.INSTANCE.convert2ReqDTO(tag));
        return Resp.success();
    }

}
