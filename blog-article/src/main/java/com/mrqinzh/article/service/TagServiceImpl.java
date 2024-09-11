package com.mrqinzh.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.entity.Tag;
import com.mrqinzh.article.mapper.TagMapper;
import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

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
            throw new BizException(ErrorCode.BAD_PARAMETER, "必须上传标签对应图片！");
        }
        tagMapper.insert(tag);
    }

    @Override
    public Resp delete(Integer id) {
        tagMapper.deleteById(id);
        return Resp.success();
    }

    @Override
    public void update(Tag tag) {
        if (tag.getId() == null || StringUtils.isBlank(tag.getCoverImg())) {
            throw new BizException(ErrorCode.BAD_PARAMETER);
        }
        tagMapper.updateById(tag);
    }

    @Override
    public Tag getById(Integer id) {
        return tagMapper.selectById(id);
    }
}
