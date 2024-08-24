package com.mrqinzh.article.api;

public interface ArticleApiService {

    Article safeGet(Long id);

    Article get(Long id);

    Page<Article> list(PageDTO pageDTO); // 排序全部文章、以及分页

    Article getDetail(Long articleId); // 根据文章id展示当前文章

    void addView(Long articleId);

    void add(ArticleVo articleVo);

    void update(ArticleVo articleVo); // 修改文章内容

    void delete(Long articleId); // 删除一篇文章

}