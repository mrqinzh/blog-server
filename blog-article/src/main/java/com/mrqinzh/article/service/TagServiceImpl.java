package com.mrqinzh.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.dal.repo.TagRepository;
import com.mrqinzh.article.domain.bo.TagBO;
import com.mrqinzh.article.domain.convert.TagConvert;
import com.mrqinzh.article.domain.dto.TagReqDTO;
import com.mrqinzh.article.domain.dto.TagRespDTO;
import com.mrqinzh.article.dal.mapper.TagMapper;
import com.mrqinzh.framework.common.domain.page.PageRequest;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.utils.BeanUtils;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagRepository tagRepository;
    @Resource
    private TagMapper tagMapper;

    @Override
    public Page<TagRespDTO> page(PageRequest pageReq) {
        Page<TagBO> tagPage = tagRepository.page(pageReq);
        return PageUtils.convert(tagPage, TagConvert.INSTANCE::convert2RespDTO);
    }

    @Override
    public List<TagRespDTO> getByLimit() {
        List<TagBO> tagBOS = tagRepository.queryByLimit();
        return BeanUtils.convertList(tagBOS, TagConvert.INSTANCE::convert2RespDTO);
    }

    @Override
    public void add(TagReqDTO tag) {
        if (StringUtils.isBlank(tag.getCoverImg())) {
            throw new BizException(ErrorCode.BAD_PARAMETER, "必须上传标签对应图片！");
        }
        tagRepository.insert(TagConvert.INSTANCE.convert2BO(tag));
    }

    @Override
    public void delete(Long id) {
        tagMapper.deleteById(id);
    }

    @Override
    public void update(TagReqDTO tag) {
        if (tag.getId() == null) {
            return;
        }
        tagRepository.updateById(TagConvert.INSTANCE.convert2BO(tag));
    }

    @Override
    public TagRespDTO getById(Long id) {
        TagBO tagBO = tagRepository.queryById(id);
        return TagConvert.INSTANCE.convert2RespDTO(tagBO);
    }

    @Override
    public List<TagRespDTO> queryByName(String name) {
        List<TagBO> tagBOS = tagRepository.queryByName(name);
        return BeanUtils.convertList(tagBOS, TagConvert.INSTANCE::convert2RespDTO);
    }

    @Override
    public List<TagRespDTO> queryByArticleId(Long articleId) {
        return BeanUtils.convertList(tagRepository.queryByArticleId(articleId), TagConvert.INSTANCE::convert2RespDTO);
    }
}
