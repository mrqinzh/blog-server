package com.mrqinzh.apis.tag;

import com.mrqinzh.common.entity.Tag;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;

import java.util.List;

public interface TagService {

    /**
     * 分页查询 tag
     */
    Resp page(PageVO pageDTO);

    /**
     * 查询 tags limit 20
     */
    List<Tag> getByLimit();

    void add(Tag tag);

    Resp delete(Integer id);

    void update(Tag tag);

    Tag getById(Integer id);
}
