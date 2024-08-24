package com.mrqinzh.article.api;

import com.mrqinzh.common.domain.entity.Tag;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.dto.PageDTO;

import java.util.List;

public interface TagApiService {

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
