package com.mrqinzh.comment.dal.repo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mrqinzh.comment.dal.mapper.CommentMapper;
import com.mrqinzh.comment.domain.bo.CommentBO;
import com.mrqinzh.comment.domain.convert.CommentConvert;
import com.mrqinzh.comment.domain.dto.CommentRespDTO;
import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.vo.CommentPageDTO;
import com.mrqinzh.framework.common.utils.BeanUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRepository {

    @Resource
    private CommentMapper commentMapper;

    public void insert(CommentBO comment) {
        commentMapper.insert(CommentConvert.INSTANCE.convert(comment));
    }

    public List<CommentBO> queryByTypeId(String idType, Long id) {
        List<Comment> comments = commentMapper.getById(idType, id);
        return BeanUtils.convertList(comments, CommentConvert.INSTANCE::convert2BO);
    }

    public List<CommentBO> list(CommentPageDTO commentPageVo) {
        List<Comment> list = commentMapper.list(commentPageVo);
        return BeanUtils.convertList(list, CommentConvert.INSTANCE::convert2BO);
    }

    public void deleteByTypeId(String idType, Long id) {
        commentMapper.deleteByTypeId(idType, id);
    }

    public List<CommentBO> queryMessages() {
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getType, 2).eq(Comment::getStatus, 0));
        return BeanUtils.convertList(comments, CommentConvert.INSTANCE::convert2BO);
    }

    public List<CommentBO> getByIpOrNickname(String ip, String nickname) {
        List<Comment> list = commentMapper.getByIpOrNickname(ip, nickname);
        return BeanUtils.convertList(list, CommentConvert.INSTANCE::convert2BO);
    }
}
