package com.mrqinzh.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mrqinzh.common.domain.entity.Comment;
import com.mrqinzh.common.domain.vo.comment.CommentPageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    Boolean add(Comment comment);

    @Select("select * from comment where ip = #{ip} or nickname = #{nickname}")
    List<Comment> getByIpOrNickname(@Param("ip") String ip, @Param("nickname") String nickname);

    /**
     * 根据id 查询评论信息
     * @param idType id类型： userId、articleId、commentId
     * @param id id值
     */
    List<Comment> getById(@Param("idType") String idType, @Param("id") Integer id);

    /**
     * 根据 id 删除评论信息
     * @param idType id类型： userId、articleId、commentId
     */
    Boolean deleteByTypeId(@Param("idType") String idType, @Param("id") Integer id);

    List<Comment> list(CommentPageDTO commentPageVo);
}
