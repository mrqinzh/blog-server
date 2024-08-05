package com.mrqinzh.webapp.controller;

import com.mrqinzh.common.entity.Article;
import com.mrqinzh.common.entity.User;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.exception.BizException;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.article.ArticleVo;
import com.mrqinzh.webapp.secure.authentication.context.AuthenticationContextUtils;
import com.mrqinzh.webapp.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Api(tags = "文章接口")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;
//    @Autowired
//    private GlobalMessageProducer producer;

    @ApiOperation(value = "根据 articleId 查询文章具体信息")
    @GetMapping("/{articleId}")
    public Resp getById(@PathVariable("articleId") Integer articleId) {
        Article article = articleService.getDetail(articleId);

        CompletableFuture.runAsync(() -> {
            articleService.addView(article.getId());
        });
        return DataResp.ok(article);
    }

    @ApiOperation(value = "分页加载文章列表")
    @GetMapping("/list")
    public Resp list(PageVO pageVO) {
        User user = AuthenticationContextUtils.getUser();
//        if (user != null) {
//            String message = user.getName() + "刚刚浏览了文章列表，请注意查收。";
//            WebSocketBean webSocketBean = new WebSocketBean(false, message);
//            producer.send(WebSocketMessage.TOPIC, new WebSocketMessage(webSocketBean, SecurityProperties.PROJECT_DEVELOPER_ID));
//        }
        return articleService.list(pageVO);
    }

//    @AccessPermission(RoleType.SUPER_ADMIN)
    @ApiOperation(value = "添加文章")
    @PostMapping("/add")
    public Resp add(@RequestBody @Valid ArticleVo articleVo) {
        articleService.add(articleVo);
        return Resp.sendMsg(AppStatus.INSERT_SUCCESS);
    }

//    @AccessPermission(RoleType.SUPER_ADMIN)
    @ApiOperation(value = "根据 articleId 更新文章")
    @PostMapping("/update")
    public Resp update(@RequestBody ArticleVo articleVo){
        if (articleVo.getId() == null) {
            throw new BizException(AppStatus.BAD_PARAMETER_REQUEST);
        }
        articleService.update(articleVo);
        return Resp.sendMsg(AppStatus.UPDATE_SUCCESS);
    }

//    @AccessPermission(RoleType.SUPER_ADMIN)
    @ApiOperation(value = "根据 id 删除文章")
    @DeleteMapping("/{articleId}")
    public Resp delete(@PathVariable("articleId") Integer articleId){
        articleService.delete(articleId);
        return Resp.sendMsg(AppStatus.DELETE_SUCCESS);
    }

}