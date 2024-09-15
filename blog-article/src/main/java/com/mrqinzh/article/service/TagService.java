package com.mrqinzh.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.dto.TagReqDTO;
import com.mrqinzh.article.domain.dto.TagRespDTO;
import com.mrqinzh.framework.common.domain.page.PageCondition;

import java.util.List;

public interface TagService {

    /**
     * 分页查询 tag
     */
    Page<TagRespDTO> page(PageCondition pageReq);

    /**
     * 查询 tags limit 20
     */
    List<TagRespDTO> getByLimit();

    void add(TagReqDTO tag);

    void delete(Long id);

    void update(TagReqDTO tag);

    TagRespDTO getById(Long id);

    List<TagRespDTO> queryByName(String name);
}
