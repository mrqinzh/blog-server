package com.mrqinzh.article.dal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.article.domain.entity.Article;
import com.mrqinzh.framework.common.domain.page.PageCondition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    long pageCount(PageCondition pageReq);
    List<Article> list(PageCondition pageReq); // 排序全部文章、以及分页

    Article getById(Long articleId); // 根据文章id展示当前文章

    @Update("update article set status = 1 where id = #{articleId}")
    Boolean deleteStatus(Long articleId); // 删除一篇文章

}