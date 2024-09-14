package com.mrqinzh.comment.controller.admin;

import com.mrqinzh.comment.domain.convert.CommentConvert;
import com.mrqinzh.comment.domain.dto.CommentRespDTO;
import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.vo.CommentPageDTO;
import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.comment.service.CommentService;
import com.mrqinzh.framework.common.resp.CollectionDataResp;
import com.mrqinzh.framework.common.resp.DataResp;
import com.mrqinzh.framework.common.resp.PageResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.web.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "评论接口")
@RestController
@RequestMapping("comment")
public class CommentController extends BaseController {

    @Resource
    private CommentService commentService;

    @Operation(summary = "分页获取所有评论信息")
    @GetMapping("list")
    public Resp list(CommentPageDTO commentPageVo) {
        List<CommentRespDTO> comments = commentService.list(commentPageVo);
        return CollectionDataResp.ok(comments, CommentConvert.INSTANCE::convert2RespVO);
    }

    @Operation(summary = "添加一条评论/留言，任何人均可添加")
    @PostMapping("add")
    public Resp add(@RequestBody @Valid CommentReqVO commentReqVo) {
        commentReqVo.setCommentIp(getClientIp());
        commentService.add(CommentConvert.INSTANCE.convert2ReqDTO(commentReqVo));
        return Resp.success();
    }

    /**
     * 根据对用的 id 类型， 对评论信息进行筛选
     * @param idType id类型： userId、articleId
     */
    @Operation(summary = "根据 userId、articleId 查询评论")
    @GetMapping("{idType}/{id}")
    public Resp getById(@PathVariable String idType, @PathVariable Long id) {
        List<CommentRespDTO> list = commentService.getById(idType, id);
        return CollectionDataResp.ok(list, CommentConvert.INSTANCE::convert2RespVO);
    }

    @Operation(summary = "根据(*id)删除评论")
    @DeleteMapping("{idType}/{id}")
    public Resp deleteById(@PathVariable String idType, @PathVariable Long id) {
        commentService.deleteById(idType, id);
        return Resp.success();
    }

    @Operation(summary = "获取留言信息")
    @GetMapping("message-list")
    public Resp getMessageList() {
        List<CommentRespDTO> comments = commentService.getMessageList();
        return CollectionDataResp.ok(comments, CommentConvert.INSTANCE::convert2RespVO);
    }

}
