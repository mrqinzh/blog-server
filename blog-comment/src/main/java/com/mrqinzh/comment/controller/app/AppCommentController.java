package com.mrqinzh.comment.controller.app;

import com.mrqinzh.comment.controller.BaseCommentController;
import com.mrqinzh.comment.domain.convert.CommentConvert;
import com.mrqinzh.comment.domain.dto.CommentRespDTO;
import com.mrqinzh.comment.domain.dto.CommentPageReqDTO;
import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.comment.service.CommentService;
import com.mrqinzh.framework.common.resp.CollectionDataResp;
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
        List<CommentRespDTO> comments = commentService.getMessageList();
        return CollectionDataResp.ok(comments, CommentConvert.INSTANCE::convert2RespVO);
    }

    @GetMapping("list")
    public Resp list(CommentPageReqDTO commentPageVo) {
        List<CommentRespDTO> comments = commentService.list(commentPageVo);
        return CollectionDataResp.ok(comments, CommentConvert.INSTANCE::convert2RespVO);
    }

    @GetMapping("{idType}/{id}")
    public Resp getById(@PathVariable("idType") String idType, @PathVariable("id") Long id) {
        List<CommentRespDTO> list = commentService.getById(idType, id);
        return CollectionDataResp.ok(list, CommentConvert.INSTANCE::convert2RespVO);
    }

    @PostMapping("add")
    public Resp add(@RequestBody @Valid CommentReqVO commentReqVO) {
        commentReqVO.setCommentIp(getClientIp());
        commentService.add(CommentConvert.INSTANCE.convert2ReqDTO(commentReqVO));
        return Resp.success();
    }

}
