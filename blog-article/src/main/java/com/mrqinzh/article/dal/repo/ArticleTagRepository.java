package com.mrqinzh.article.dal.repo;

import com.mrqinzh.article.dal.mapper.ArticleTagMapper;
import com.mrqinzh.article.domain.entity.ArticleTagRelation;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ArticleTagRepository {

    @Resource
    private ArticleTagMapper articleTagMapper;

    public void insert(Long articleId, Long tagId, String tagName) {
        ArticleTagRelation relation = new ArticleTagRelation(articleId, tagId, tagName);
        articleTagMapper.insert(relation);
    }

}
