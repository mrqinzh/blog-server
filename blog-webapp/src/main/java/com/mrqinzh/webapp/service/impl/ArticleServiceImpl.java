package com.mrqinzh.webapp.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.common.domain.entity.Article;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.vo.article.ArticleVo;
import com.mrqinzh.webapp.client.ArticleClient;
import com.mrqinzh.webapp.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleClient articleClient;

    @Override
    public Article safeGet(Long id) {
        return articleClient.safeGet(id);
    }

    @Override
    public Article get(Long id) {
        return articleClient.get(id);
    }

    @Override
    public Page<Article> list(PageDTO pageDTO) {
        return articleClient.list(pageDTO);
    }

    @Override
    public Article getDetail(Long articleId) {
        return articleClient.getDetail(articleId);
    }

    @Override
    public void addView(Long articleId) {
        articleClient.addView(articleId);
    }

    @Override
    public void add(ArticleVo articleVo) {
        articleClient.add(articleVo);
    }

    @Override
    public void update(ArticleVo articleVo) {
        articleClient.update(articleVo);
    }

    @Override
    public void delete(Long articleId) {
        articleClient.delete(articleId);
    }
}
