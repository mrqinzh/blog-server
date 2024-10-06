package com.mrqinzh.article.service;

import com.mrqinzh.article.dal.repo.ArticleTagRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl implements ArticleTagService {

    @Resource
    private ArticleTagRepository articleTagRepository;

    @Override
    public void add(Long articleId, Long tagId, String tagName) {
        articleTagRepository.insert(articleId, tagId, tagName);
    }

}
