package com.mrqinzh.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.common.entity.Comment;
import com.mrqinzh.common.vo.comment.CommentPageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    Boolean add(Comment comment);

    @Select("select * from comment where ip = #{ip} or nickname = #{nickname}")
    List<Comment> getByIpOrNickname(String ip, String nickname);

    /**
     * 根据id 查询评论信息
     * @param idType id类型： userId、articleId、commentId
     * @param id id值
     */
    List<Comment> getById(String idType, Integer id);

    /**
     * 根据 id 删除评论信息
     * @param idType id类型： userId、articleId、commentId
     */
    Boolean deleteByTypeId(String idType, Integer id);

    List<Comment> list(CommentPageVo commentPageVo);
}
