package com.mrqinzh.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.common.domain.entity.Article;
import com.mrqinzh.common.domain.dto.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    long pageCount(PageDTO pageDTO);
    List<Article> list(PageDTO pageDTO); // 排序全部文章、以及分页

    Article getById(Integer articleId); // 根据文章id展示当前文章

    @Update("update article set status = 1 where id = #{articleId}")
    Boolean deleteStatus(Integer articleId); // 删除一篇文章

}