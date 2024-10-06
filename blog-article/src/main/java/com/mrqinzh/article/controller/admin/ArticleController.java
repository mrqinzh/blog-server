package com.mrqinzh.article.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.convert.ArticleConvert;
import com.mrqinzh.article.domain.dto.ArticleRespDTO;
import com.mrqinzh.article.domain.vo.ArticleReqVO;
import com.mrqinzh.article.domain.vo.ArticleRespVO;
import com.mrqinzh.article.domain.vo.TagRespVO;
import com.mrqinzh.article.service.ArticleService;
import com.mrqinzh.framework.common.domain.page.PageRequest;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.utils.CollectionUtils;
import com.mrqinzh.framework.common.utils.MyUtil;
import com.mrqinzh.framework.common.web.controller.BaseController;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
        ArticleRespDTO article = articleService.getDetail(articleId);

        // 后台展示不新增
//        CompletableFuture.runAsync(() -> {
//            articleService.addView(article.getId());
//        });
        return DataResp.ok(ArticleConvert.INSTANCE.convert2RespVO(article));
    }

    @Operation(summary = "分页加载文章列表")
    @GetMapping("/list")
    public Resp list(PageRequest pageReq) {
//        if (user != null) {
//            String message = user.getName() + "刚刚浏览了文章列表，请注意查收。";
//            WebSocketBean webSocketBean = new WebSocketBean(false, message);
//            producer.syncSend(WebSocketMessage.TOPIC, new WebSocketMessage(webSocketBean, SecurityProperties.PROJECT_DEVELOPER_ID));
//        }
        Page<ArticleRespVO> page = PageUtils.convert(articleService.Page(pageReq), ArticleConvert.INSTANCE::convert2RespVO);
        page.getRecords().forEach(el -> {
            if (CollectionUtils.isNotEmpty(el.getTags())) {
                el.setTag(el.getTags().stream().map(TagRespVO::getName).collect(Collectors.joining(",")));
            }
        });
        return PageUtils.resp(page);
    }

    @Operation(summary = "添加文章")
    @PostMapping("/add")
    public Resp add(@RequestBody @Valid ArticleReqVO articleReqVO) {
        articleReqVO.setArticleSummary(MyUtil.stripHtml(articleReqVO.getArticleSummary()));
        articleService.add(ArticleConvert.INSTANCE.convert2ReqDTO(articleReqVO));
        return Resp.success();
    }

    @Operation(summary = "根据 articleId 更新文章")
    @PostMapping("/update")
    public Resp update(@RequestBody ArticleReqVO articleReqVo){
        if (articleReqVo.getId() == null) {
            return Resp.error(ErrorCode.BAD_PARAMETER);
        }
        articleService.update(ArticleConvert.INSTANCE.convert2ReqDTO(articleReqVo));
        return Resp.success();
    }

    @Operation(summary = "根据 id 删除文章")
    @DeleteMapping("/{articleId}")
    public Resp delete(@PathVariable("articleId") Long articleId){
        articleService.delete(articleId);
        return Resp.success();
    }

    @GetMapping("count")
    public Resp count() {
        return DataResp.ok(articleService.count());
    }

}