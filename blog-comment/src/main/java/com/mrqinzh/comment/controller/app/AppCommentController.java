package com.mrqinzh.comment.controller.app;

import com.mrqinzh.comment.controller.BaseCommentController;
import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.vo.CommentPageDTO;
import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.comment.service.CommentService;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.Resp;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comment")
public class AppCommentController extends BaseCommentController {

    @Resource
    private CommentService commentService;

    @Operation(summary = "获取留言信息")
    @GetMapping("message-list")
    public Resp getMessageList() {
        List<Comment> comments = commentService.getMessageList();
        return DataResp.ok(comments);
    }

    @GetMapping("list")
    public Resp list(CommentPageDTO commentPageVo) {
        List<Comment> comments = commentService.list(commentPageVo);
        return DataResp.ok(comments);
    }

    @GetMapping("{idType}/{id}")
    public Resp getById(@PathVariable("idType") String idType, @PathVariable("id") Long id) {
        return commentService.getById(idType, id);
    }

    @PostMapping("add")
    public Resp add(@RequestBody @Valid CommentReqVO commentReqVO) {
        commentReqVO.setCommentIp(getClientIp());
        commentService.add(commentReqVO);
        return Resp.success();
    }

}
