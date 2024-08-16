package com.mrqinzh.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.common.entity.Tag;
import com.mrqinzh.common.vo.PageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> page(PageVO pageVO);

    @Select("select * from tag where id = #{id}")
    Tag getById(Integer id);
}
