package com.mrqinzh.article.dal.repo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.dal.mapper.ArticleMapper;
import com.mrqinzh.article.domain.bo.ArticleBO;
import com.mrqinzh.article.domain.convert.ArticleConvert;
import com.mrqinzh.article.domain.entity.Article;
import com.mrqinzh.framework.common.domain.page.PageCondition;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ArticleRepository {

    @Resource
    private ArticleMapper articleMapper;

    public Page<ArticleBO> page(PageCondition pageReq) {
        Page<Article> page = new Page<>(pageReq.getCurrentPage(), pageReq.getPageSize());

        page = articleMapper.selectPage(page, null);

        return PageUtils.convert(page, ArticleConvert.INSTANCE::convert2BO);
    }

    public void insert(ArticleBO articleBO) {
        articleMapper.insert(ArticleConvert.INSTANCE.convert(articleBO));
    }

    public ArticleBO queryById(Long id) {
        Article article = articleMapper.selectById(id);
        return ArticleConvert.INSTANCE.convert2BO(article);
    }

    public void updateById(ArticleBO articleBO) {
        Article article = ArticleConvert.INSTANCE.convert(articleBO);
        articleMapper.updateById(article);
    }

    public void delete(Long articleId) {
        articleMapper.deleteStatus(articleId);
    }
}
