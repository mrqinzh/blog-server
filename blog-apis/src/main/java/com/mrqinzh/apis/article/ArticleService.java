package com.mrqinzh.apis.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.common.domain.entity.Article;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.vo.article.ArticleVo;

public interface ArticleService {

    Article safeGet(Long id);

    Article get(Integer id);

    Page<Article> list(PageDTO pageDTO); // 排序全部文章、以及分页

    Article getDetail(Integer articleId); // 根据文章id展示当前文章

    void addView(Integer articleId);

    void add(ArticleVo articleVo);

    void update(ArticleVo articleVo); // 修改文章内容

    void delete(Integer articleId); // 删除一篇文章

}