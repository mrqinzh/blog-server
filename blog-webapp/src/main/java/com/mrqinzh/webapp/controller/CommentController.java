package com.mrqinzh.webapp.controller;

import com.mrqinzh.common.entity.Comment;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.enums.RoleType;
import com.mrqinzh.common.resp.DataResp;
import com.mrqinzh.common.resp.PageResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.comment.CommentPageVo;
import com.mrqinzh.common.vo.comment.CommentVo;
import com.mrqinzh.webapp.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(tags = "评论接口")
@RestController
@RequestMapping("comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @ApiOperation(value = "分页获取所有评论信息")
    @GetMapping("list")
    public Resp list(CommentPageVo commentPageVo) {
        List<Comment> comments = commentService.list(commentPageVo);
        return PageResp.ok(comments);
    }

    @ApiOperation(value = "添加一条评论/留言，任何人均可添加")
    @PostMapping("add")
    public Resp add(@RequestBody @Valid CommentVo commentVo) {
        commentService.add(commentVo);
        return Resp.sendMsg(AppStatus.INSERT_SUCCESS);
    }

    /**
     * 根据对用的 id 类型， 对评论信息进行筛选
     * @param idType id类型： userId、articleId
     */
    @ApiOperation(value = "根据 userId、articleId 查询评论")
    @GetMapping("{idType}/{id}")
    public Resp getById(@PathVariable String idType, @PathVariable Integer id) {
        return commentService.getById(idType, id);
    }

    @ApiOperation(value = "根据(*id)删除评论")
    @DeleteMapping("{idType}/{id}")
//    @AccessPermission(RoleType.SUPER_ADMIN)
    public Resp deleteById(@PathVariable String idType, @PathVariable Integer id) {
        return commentService.deleteById(idType, id);
    }

    @ApiOperation(value = "获取留言信息")
    @GetMapping("message-list")
    public Resp getMessageList() {
        List<Comment> comments = commentService.getMessageList();
        return DataResp.ok(comments);
    }

}
