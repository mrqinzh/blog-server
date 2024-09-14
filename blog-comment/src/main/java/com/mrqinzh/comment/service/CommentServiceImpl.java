package com.mrqinzh.comment.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrqinzh.comment.dal.repo.CommentRepository;
import com.mrqinzh.comment.domain.convert.CommentConvert;
import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.vo.CommentPageDTO;
import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.comment.dal.mapper.CommentMapper;
import com.mrqinzh.framework.common.resp.CollectionDataResp;
import com.mrqinzh.framework.common.resp.Resp;
import com.mrqinzh.framework.common.utils.MyUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private static final Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentRepository commentRepository;

    @Override
    public List<Comment> list(CommentPageDTO commentPageVo) {
        return commentRepository.list(commentPageVo);
    }

    @Override
    public List<Comment> getMessageList() {
        return commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getType, 2).eq(Comment::getStatus, 0));
    }

    @Override
    public void add(CommentReqVO commentReqVO) {
        Comment comment = CommentConvert.INSTANCE.convert(commentReqVO);
        comment.setContent(commentReqVO.getCommentContent());

        String ip = commentReqVO.getCommentIp();
        // 先根据 ip/昵称 查询当前用户是否已经进行过评论
        List<Comment> commentsByIp = commentMapper.getByIpOrNickname(ip, commentReqVO.getNickname());
        String avatar;
        if (!commentsByIp.isEmpty() && StringUtils.isNotBlank(commentsByIp.get(0).getAvatar())) {
            avatar = commentsByIp.get(0).getAvatar();
        } else {
            avatar = MyUtil.getRandomAvatarUrl();
        }

        comment.setAvatar(avatar);
        comment.setIp(ip);

        commentRepository.insert(comment);
    }

    @Override
    public Resp getById(String idType, Long id) {

        List<Comment> comments = commentRepository.queryByTypeId(idType, id);
        // 遍历comments，将子评论添加到对应的父评论下面
        comments.forEach(comment -> {
            if (comment.getParentId() == 0) {
                comment.setComments(comments.stream().filter(c -> c.getParentId().equals(comment.getId())).toList());
            }
        });
        List<Comment> list = comments.stream().filter(c -> c.getParentId() == 0).toList();
        return CollectionDataResp.ok(list, CommentConvert.INSTANCE::convert);
    }

    /**
     * 根据id 删除评论
     * @param idType userId、articleId、commentId 分别对应不同的id类型
     * @param id id 值
     * @return
     */
    @Override
    public Resp deleteById(String idType, Long id) {
        commentRepository.deleteByTypeId(idType, id);
        return Resp.success();
    }

    @Override
    public void deleteByTypeId(String articleOrCommentId, Long id) {
        deleteById(articleOrCommentId, id);
    }
}
