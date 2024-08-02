package com.mrqinzh.webapp.client.impl;

import com.mrqinzh.apis.article.ArticleService;
import com.mrqinzh.common.entity.Article;
import com.mrqinzh.common.resp.PageResp;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.article.ArticleVo;
import com.mrqinzh.webapp.client.ArticleClient;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

@Component
public class ArticleClientImpl implements ArticleClient {

    @DubboReference
    private ArticleService articleService;

    @Override
    public Article safeGet(Long id) {
        return articleService.safeGet(id);
    }

    @Override
    public Article get(Integer id) {
        return articleService.get(id);
    }

    @Override
    public PageResp<Article> list(PageVO pageVO) {
        return articleService.list(pageVO);
    }

    @Override
    public Article getDetail(Integer articleId) {
        return articleService.getDetail(articleId);
    }

    @Override
    public void addView(Integer articleId) {
        articleService.addView(articleId);
    }

    @Override
    public void add(ArticleVo articleVo) {
        articleService.add(articleVo);
    }

    @Override
    public void update(ArticleVo articleVo) {
        articleService.update(articleVo);
    }

    @Override
    public void delete(Integer articleId) {
        articleService.delete(articleId);
    }
}
