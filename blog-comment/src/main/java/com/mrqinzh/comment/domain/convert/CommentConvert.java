package com.mrqinzh.comment.domain.convert;

import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.comment.domain.vo.CommentRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentConvert {

    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    @Mappings(
            @Mapping(source = "commentContent", target = "content")
    )
    Comment convert(CommentReqVO commentReqVO);

    @Mappings(
            @Mapping(source = "content", target = "commentContent")
    )
    CommentRespVO convert(Comment comment);
}
