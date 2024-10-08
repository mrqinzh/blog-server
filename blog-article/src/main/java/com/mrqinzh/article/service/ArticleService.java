package com.mrqinzh.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.dto.ArticleReqDTO;
import com.mrqinzh.article.domain.dto.ArticleRespDTO;
import com.mrqinzh.framework.common.domain.page.PageRequest;

public interface ArticleService {

    ArticleRespDTO safeGet(Long id);

    ArticleRespDTO get(Long id);

    Page<ArticleRespDTO> Page(PageRequest pageReq); // 排序全部文章、以及分页

    ArticleRespDTO getDetail(Long articleId); // 根据文章id展示当前文章

    void addView(Long articleId);

    void add(ArticleReqDTO articleReqDTO);

    void update(ArticleReqDTO articleReqVo); // 修改文章内容

    void delete(Long articleId); // 删除一篇文章

    void upgradeArticleTag();

    long count();
}
