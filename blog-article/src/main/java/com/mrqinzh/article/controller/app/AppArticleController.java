package com.mrqinzh.article.controller.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.convert.ArticleConvert;
import com.mrqinzh.article.domain.dto.ArticleRespDTO;
import com.mrqinzh.article.service.ArticleService;
import com.mrqinzh.framework.common.domain.page.PageCondition;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
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
        ArticleRespDTO article = articleService.getDetail(articleId);

        CompletableFuture.runAsync(() -> {
            articleService.addView(article.getId());
        });
        return DataResp.ok(ArticleConvert.INSTANCE.convert2RespVO(article));
    }

    @Operation(summary = "分页加载文章列表")
    @GetMapping("/list")
    public Resp list(PageCondition pageReq) {
        Page<ArticleRespDTO> page = articleService.Page(pageReq);
        return PageUtils.resp(page);
    }

//    @GetMapping("/upgrade/articleTag")
    public Resp upgradeArticleTag() {
        articleService.upgradeArticleTag();
        return Resp.success();
    }

}
