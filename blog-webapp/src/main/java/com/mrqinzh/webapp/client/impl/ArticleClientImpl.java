package com.mrqinzh.webapp.client.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.apis.article.ArticleService;
import com.mrqinzh.common.domain.entity.Article;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.vo.article.ArticleVo;
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
    public Page<Article> list(PageDTO pageDTO) {
        return articleService.list(pageDTO);
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
