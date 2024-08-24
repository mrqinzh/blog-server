package com.mrqinzh.article.service;

import com.mrqinzh.article.domain.entity.Tag;
import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.resp.Resp;

import java.util.List;

public interface TagService {

    /**
     * 分页查询 tag
     */
    Resp page(PageDTO pageDTO);

    /**
     * 查询 tags limit 20
     */
    List<Tag> getByLimit();

    void add(Tag tag);

    Resp delete(Integer id);

    void update(Tag tag);

    Tag getById(Integer id);

}
