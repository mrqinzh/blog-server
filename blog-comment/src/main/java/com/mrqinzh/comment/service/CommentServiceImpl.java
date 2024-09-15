package com.mrqinzh.comment.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.comment.dal.repo.CommentRepository;
import com.mrqinzh.comment.domain.bo.CommentBO;
import com.mrqinzh.comment.domain.convert.CommentConvert;
import com.mrqinzh.comment.domain.dto.ApplyCommentReqDTO;
import com.mrqinzh.comment.domain.dto.CommentReqDTO;
import com.mrqinzh.comment.domain.dto.CommentRespDTO;
import com.mrqinzh.comment.domain.enums.CommentStatus;
import com.mrqinzh.comment.domain.dto.CommentPageReqDTO;
import com.mrqinzh.comment.utils.PageUtils;
import com.mrqinzh.framework.common.utils.BeanUtils;
import com.mrqinzh.framework.common.utils.CollectionUtils;
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
    private CommentRepository commentRepository;

    @Override
    public Page<CommentRespDTO> page(CommentPageReqDTO commentPageReqDTO) {
        Page<CommentBO> res = commentRepository.page(commentPageReqDTO);
        return PageUtils.convert(res, CommentConvert.INSTANCE::convert2RespDTO);
    }

    @Override
    public List<CommentRespDTO> list(CommentPageReqDTO commentPageVo) {
        List<CommentBO> list = commentRepository.list(commentPageVo);

        return BeanUtils.convertList(list, CommentConvert.INSTANCE::convert2RespDTO);
    }

    @Override
    public List<CommentRespDTO> getMessageList() {
        List<CommentBO> commentBOS = commentRepository.queryMessages();
        return BeanUtils.convertList(commentBOS, CommentConvert.INSTANCE::convert2RespDTO);
    }

    @Override
    public void add(CommentReqDTO commentReqDTO) {
        CommentBO comment = CommentConvert.INSTANCE.convert2BO(commentReqDTO);
        comment.setContent(commentReqDTO.getCommentContent());

        String ip = commentReqDTO.getCommentIp();
        // 先根据 ip/昵称 查询当前用户是否已经进行过评论
        List<CommentBO> commentsByIp = commentRepository.queryByIpOrNickname(ip, commentReqDTO.getNickname());
        String avatar;
        if (!commentsByIp.isEmpty() && StringUtils.isNotBlank(commentsByIp.get(0).getAvatar())) {
            avatar = commentsByIp.get(0).getAvatar();
        } else {
            avatar = MyUtil.getRandomAvatarUrl();
        }
        comment.setStatus(CommentStatus.APPLYING);
        comment.setAvatar(avatar);
        comment.setIp(ip);

        commentRepository.insert(comment);
    }

    @Override
    public List<CommentRespDTO> getById(String idType, Long id) {

        List<CommentRespDTO> commentRespDTOS = BeanUtils.convertList(commentRepository.queryByTypeId(idType, id), CommentConvert.INSTANCE::convert2RespDTO);
        // 遍历comments，将子评论添加到对应的父评论下面
        commentRespDTOS.forEach(comment -> {
            if (comment.getParentId() == 0) {
                comment.setComments(commentRespDTOS.stream().filter(c -> c.getParentId().equals(comment.getId())).toList());
            }
        });
        List<CommentRespDTO> list = commentRespDTOS.stream().filter(c -> c.getParentId() == 0).toList();
        return list;
    }

    /**
     * 根据id 删除评论
     * @param idType userId、articleId、commentId 分别对应不同的id类型
     * @param id id 值
     * @return
     */
    @Override
    public void deleteById(String idType, Long id) {
        commentRepository.deleteByTypeId(idType, id);
    }

    @Override
    public void applyComments(ApplyCommentReqDTO applyCommentReqDTO) {
        List<Long> commentIds = applyCommentReqDTO.getCommentIds();
        if (CollectionUtils.isEmpty(commentIds)) {
            return;
        }
        List<CommentBO> commentBOS = commentRepository.queryByIds(commentIds);
        if (CollectionUtils.isEmpty(commentBOS)) {
            return;
        }

        commentBOS.stream()
                .filter(commentBO -> commentBO.getStatus() == CommentStatus.APPLYING)
                .forEach(commentBO -> {
                    if (applyCommentReqDTO.isApprove()) {
                        commentBO.setStatus(CommentStatus.NORMAL);
                    } else {
                        commentBO.setStatus(CommentStatus.REJECT);
                    }
                });

        commentRepository.update(commentBOS);
    }
}
