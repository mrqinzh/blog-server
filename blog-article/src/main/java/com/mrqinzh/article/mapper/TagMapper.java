package com.mrqinzh.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.common.domain.entity.Tag;
import com.mrqinzh.common.domain.dto.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    @Select("select * from tag where id = #{id}")
    Tag getById(Integer id);
}
