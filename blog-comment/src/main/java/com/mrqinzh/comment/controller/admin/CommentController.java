package com.mrqinzh.comment.controller.admin;

import com.mrqinzh.common.domain.entity.Comment;
import com.mrqinzh.common.domain.enums.AppStatus;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.vo.comment.CommentPageDTO;
import com.mrqinzh.common.domain.vo.comment.CommentVo;
import com.mrqinzh.gateway.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "评论接口")
@RestController
@RequestMapping("comment")
public class CommentController extends AbstractController {

    @Resource
    private CommentService commentService;

    @Operation(summary = "分页获取所有评论信息")
    @GetMapping("list")
    public Resp list(CommentPageDTO commentPageVo) {
        List<Comment> comments = commentService.list(commentPageVo);
        return DataResp.ok(comments);
    }

    @Operation(summary = "添加一条评论/留言，任何人均可添加")
    @PostMapping("add")
    public Resp add(@RequestBody @Valid CommentVo commentVo) {
        commentVo.setCommentIp(getClientIp());
        commentService.add(commentVo);
        return Resp.sendMsg(AppStatus.INSERT_SUCCESS);
    }

    /**
     * 根据对用的 id 类型， 对评论信息进行筛选
     * @param idType id类型： userId、articleId
     */
    @Operation(summary = "根据 userId、articleId 查询评论")
    @GetMapping("{idType}/{id}")
    public Resp getById(@PathVariable String idType, @PathVariable Long id) {
        return commentService.getById(idType, id);
    }

    @Operation(summary = "根据(*id)删除评论")
    @DeleteMapping("{idType}/{id}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp deleteById(@PathVariable String idType, @PathVariable Long id) {
        return commentService.deleteById(idType, id);
    }

    @Operation(summary = "获取留言信息")
    @GetMapping("message-list")
    public Resp getMessageList() {
        List<Comment> comments = commentService.getMessageList();
        return DataResp.ok(comments);
    }

}
