package com.mrqinzh.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.entity.Article;
import com.mrqinzh.article.domain.vo.ArticleVO;
import com.mrqinzh.framework.common.domain.pojo.page.BasePageReq;

public interface ArticleService {

    Article safeGet(Long id);

    Article get(Long id);

    Page<Article> list(BasePageReq pageReq); // 排序全部文章、以及分页

    Article getDetail(Long articleId); // 根据文章id展示当前文章

    void addView(Long articleId);

    void add(ArticleVO articleVo);

    void update(ArticleVO articleVo); // 修改文章内容

    void delete(Long articleId); // 删除一篇文章

}
