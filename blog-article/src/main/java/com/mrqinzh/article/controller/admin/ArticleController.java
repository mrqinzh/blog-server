package com.mrqinzh.article.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.entity.Article;
import com.mrqinzh.article.domain.vo.ArticleVO;
import com.mrqinzh.article.service.ArticleService;
import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.domain.enums.AppStatus;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.PageResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Tag(name = "文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Resource
    private ArticleService articleService;
//    @Autowired
//    private GlobalMessageProducer producer;

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
    public Resp list(PageDTO pageDTO) {
//        if (user != null) {
//            String message = user.getName() + "刚刚浏览了文章列表，请注意查收。";
//            WebSocketBean webSocketBean = new WebSocketBean(false, message);
//            producer.syncSend(WebSocketMessage.TOPIC, new WebSocketMessage(webSocketBean, SecurityProperties.PROJECT_DEVELOPER_ID));
//        }
        Page<Article> page = articleService.list(pageDTO);
        return PageResp.ok(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords(), ArticleVO::new);
    }

    @Operation(summary = "添加文章")
    @PostMapping("/add")
    public Resp add(@RequestBody @Valid ArticleVO articleVo) {
        articleService.add(articleVo);
        return Resp.success();
    }

    @Operation(summary = "根据 articleId 更新文章")
    @PostMapping("/update")
    public Resp update(@RequestBody ArticleVO articleVo){
        if (articleVo.getId() == null) {
            return Resp.error(ErrorCode.BAD_PARAMETER);
        }
        articleService.update(articleVo);
        return Resp.success();
    }

    @Operation(summary = "根据 id 删除文章")
    @DeleteMapping("/{articleId}")
    public Resp delete(@PathVariable("articleId") Long articleId){
        articleService.delete(articleId);
        return Resp.success();
    }

}