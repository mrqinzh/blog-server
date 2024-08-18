package com.mrqinzh.article.rpc.provider;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.apis.tag.TagService;
import com.mrqinzh.common.domain.entity.Tag;
import com.mrqinzh.common.domain.enums.AppStatus;
import com.mrqinzh.common.exception.BizException;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.article.mapper.TagMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class TagServiceProvider implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public Resp page(PageDTO pageDTO) {
        Page<Tag> tagPage = new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize());
        tagMapper.selectPage(tagPage, new LambdaQueryWrapper<Tag>().like(Tag::getName, pageDTO.getCondition()));
        return DataResp.ok(tagPage);
    }

    @Override
    public List<Tag> getByLimit() {
        return tagMapper.selectList(new LambdaQueryWrapper<Tag>().last("limit 20"));
    }

    @Override
    public void add(Tag tag) {
        if (StringUtils.isBlank(tag.getCoverImg())) {
            throw new BizException(AppStatus.BAD_PARAMETER_REQUEST, "必须上传标签对应图片！");
        }
        tagMapper.insert(tag);
    }

    @Override
    public Resp delete(Integer id) {
        tagMapper.deleteById(id);
        return Resp.sendMsg(AppStatus.DELETE_SUCCESS);
    }

    @Override
    public void update(Tag tag) {
        if (tag.getId() == null || StringUtils.isBlank(tag.getCoverImg())) {
            throw new BizException(AppStatus.BAD_PARAMETER_REQUEST);
        }
        tagMapper.updateById(tag);
    }

    @Override
    public Tag getById(Integer id) {
        return tagMapper.selectById(id);
    }

}
