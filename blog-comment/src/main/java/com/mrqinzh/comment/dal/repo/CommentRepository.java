package com.mrqinzh.comment.dal.repo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.comment.dal.mapper.CommentMapper;
import com.mrqinzh.comment.domain.bo.CommentBO;
import com.mrqinzh.comment.domain.convert.CommentConvert;
import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.comment.domain.enums.CommentStatus;
import com.mrqinzh.comment.domain.dto.CommentPageReqDTO;
import com.mrqinzh.comment.utils.PageUtils;
import com.mrqinzh.framework.common.utils.BeanUtils;
import com.mrqinzh.framework.common.utils.CollectionUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentRepository {

    @Resource
    private CommentMapper commentMapper;

    public List<CommentBO> queryByIds(List<Long> ids) {
        return BeanUtils.convertList(commentMapper.selectBatchIds(ids), CommentConvert.INSTANCE::convert2BO);
    }

    public void update(List<CommentBO> commentBOList) {
        if (CollectionUtils.isEmpty(commentBOList)) {
            return;
        }
        commentBOList.forEach(commentBO -> {
            Comment comment = CommentConvert.INSTANCE.convert(commentBO);
            commentMapper.updateById(comment);
        });
    }

    public void insert(CommentBO comment) {
        commentMapper.insert(CommentConvert.INSTANCE.convert(comment));
    }

    public List<CommentBO> queryByTypeId(String idType, Long id) {
        List<Comment> comments = commentMapper.getById(idType, id);
        return BeanUtils.convertList(comments, CommentConvert.INSTANCE::convert2BO);
    }

    public List<CommentBO> list(CommentPageReqDTO commentPageVo) {
        List<Comment> list = commentMapper.list(commentPageVo);
        return BeanUtils.convertList(list, CommentConvert.INSTANCE::convert2BO);
    }

    public Page<CommentBO> page(CommentPageReqDTO pageReqDTO) {
        Page<Comment> page = new Page<>(pageReqDTO.getCurrentPage(), pageReqDTO.getPageSize());
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(StringUtils.isNotBlank(pageReqDTO.getNickname()), Comment::getNickname, pageReqDTO.getNickname())
                .eq(pageReqDTO.getType() != null, Comment::getType, BusinessType.getCode(pageReqDTO.getType()))
                .eq(StringUtils.isNotBlank(pageReqDTO.getIp()), Comment::getIp, pageReqDTO.getIp())
                .le(pageReqDTO.getEndTime() != null, Comment::getCreateTime, pageReqDTO.getEndTime())
                .ge(pageReqDTO.getStartTime() != null, Comment::getCreateTime, pageReqDTO.getStartTime())
                ;
        Page<Comment> commentPage = commentMapper.selectPage(page, queryWrapper);

        return PageUtils.convert(commentPage, CommentConvert.INSTANCE::convert2BO);
    }

    public void deleteByTypeId(String idType, Long id) {
        commentMapper.deleteByTypeId(idType, id);
    }

    public List<CommentBO> queryMessages() {
        List<Comment> comments = commentMapper.selectList(new LambdaQueryWrapper<Comment>().eq(Comment::getType, BusinessType.MESSAGE.ordinal()).eq(Comment::getStatus, CommentStatus.NORMAL.ordinal()));
        return BeanUtils.convertList(comments, CommentConvert.INSTANCE::convert2BO);
    }

    public List<CommentBO> queryByIpOrNickname(String ip, String nickname) {
        List<Comment> list = commentMapper.getByIpOrNickname(ip, nickname);
        return BeanUtils.convertList(list, CommentConvert.INSTANCE::convert2BO);
    }
}
