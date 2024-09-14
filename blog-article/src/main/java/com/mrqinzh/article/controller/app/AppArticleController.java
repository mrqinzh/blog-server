package com.mrqinzh.article.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.entity.Article;
import com.mrqinzh.article.domain.vo.ArticleVO;
import com.mrqinzh.article.service.ArticleService;
import com.mrqinzh.framework.common.domain.pojo.page.BasePageReq;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.PageResp;
import com.mrqinzh.framework.common.resp.Resp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/article")
public class AppArticleController {

    @Resource
    private ArticleService articleService;

    @Operation(summary = "根据 articleId 查询文章具体信息")
    @GetMapping("/{articleId}")
    public Resp getById(@PathVariable("articleId") Long articleId) {
        Article article = articleService.getDetail(articleId);

        CompletableFuture.runAsync(() -> {
            articleService.addView(article.getId());
        });
        return DataResp.ok(new ArticleVO(article));
    }

    @Operation(summary = "分页加载文章列表")
    @GetMapping("/list")
    public Resp list(BasePageReq pageReq) {
        Page<Article> page = articleService.list(pageReq);
        return PageResp.ok(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords(), ArticleVO::new);
    }

}
