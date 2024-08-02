package com.mrqinzh.webapp.service;

import com.mrqinzh.common.entity.Article;
import com.mrqinzh.common.resp.PageResp;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.article.ArticleVo;

public interface ArticleService {

    Article safeGet(Long id);

    Article get(Integer id);

    PageResp<Article> list(PageVO pageVO); // 排序全部文章、以及分页

    Article getDetail(Integer articleId); // 根据文章id展示当前文章

    void addView(Integer articleId);

    void add(ArticleVo articleVo);

    void update(ArticleVo articleVo); // 修改文章内容

    void delete(Integer articleId); // 删除一篇文章

}
