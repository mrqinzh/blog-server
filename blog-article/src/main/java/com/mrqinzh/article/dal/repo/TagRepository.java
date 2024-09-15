package com.mrqinzh.article.dal.repo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.dal.mapper.TagMapper;
import com.mrqinzh.article.domain.bo.TagBO;
import com.mrqinzh.article.domain.convert.TagConvert;
import com.mrqinzh.article.domain.entity.Tag;
import com.mrqinzh.framework.common.domain.pojo.page.PageCondition;
import com.mrqinzh.framework.common.utils.BeanUtils;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TagRepository {

    @Resource
    private TagMapper tagMapper;

    public Page<TagBO> page(PageCondition pageReq) {
        Page<Tag> page = new Page<>(pageReq.getCurrentPage(), pageReq.getPageSize());
        page = tagMapper.selectPage(page, new LambdaQueryWrapper<Tag>().like(StringUtils.isNotBlank(pageReq.getCondition()), Tag::getName, pageReq.getCondition()));
        return PageUtils.convert(page, TagConvert.INSTANCE::convert2BO);
    }

    public TagBO queryById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return TagConvert.INSTANCE.convert2BO(tag);
    }

    public List<TagBO> queryByLimit() {
        List<Tag> tags = tagMapper.selectList(new LambdaQueryWrapper<Tag>().last("limit 20"));
        return BeanUtils.convertList(tags, TagConvert.INSTANCE::convert2BO);
    }

    public List<TagBO> queryByName(String name) {
        List<Tag> tags = tagMapper.selectList(new LambdaQueryWrapper<Tag>().eq(Tag::getName, name));
        return BeanUtils.convertList(tags, TagConvert.INSTANCE::convert2BO);
    }

    public void updateById(TagBO tagBO) {
        Tag tag = TagConvert.INSTANCE.convert(tagBO);
        tagMapper.updateById(tag);
    }

    public void insert(TagBO tagBO) {
        Tag tag = TagConvert.INSTANCE.convert(tagBO);
        tagMapper.insert(tag);
    }
}
