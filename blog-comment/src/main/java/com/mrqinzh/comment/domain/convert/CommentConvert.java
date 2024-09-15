package com.mrqinzh.comment.domain.convert;

import com.mrqinzh.comment.domain.bo.CommentBO;
import com.mrqinzh.comment.domain.dto.CommentPageReqDTO;
import com.mrqinzh.comment.domain.dto.CommentReqDTO;
import com.mrqinzh.comment.domain.dto.CommentRespDTO;
import com.mrqinzh.comment.domain.entity.Comment;
import com.mrqinzh.comment.domain.enums.BusinessType;
import com.mrqinzh.comment.domain.enums.CommentStatus;
import com.mrqinzh.comment.domain.vo.CommentPageReqVO;
import com.mrqinzh.comment.domain.vo.CommentReqVO;
import com.mrqinzh.comment.domain.vo.CommentRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentConvert {

    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    /**
     * pageReqVo -> pageReqDto
     */
    CommentPageReqDTO convert2PageVO(CommentPageReqVO commentPageReqVO);

    /**
     * reqDto -> bo
     */
    CommentBO convert2BO(CommentReqDTO commentReqDTO);

    /**
     * reqVo -> reqDto
     */
    CommentReqDTO convert2ReqDTO(CommentReqVO commentReqVO);

    /**
     * respDto -> respVo
     */
    CommentRespVO convert2RespVO(CommentRespDTO commentRespDTO);

    /**
     * bo -> respDto
     */
    CommentRespDTO convert2RespDTO(CommentBO commentBO);

    /**
     * entity -> bo
     */
    @Mappings({
            @Mapping(source = "type", target = "type", qualifiedByName = "code2Type"),
            @Mapping(source = "status", target = "status", qualifiedByName = "code2Status")
    })
    CommentBO convert2BO(Comment comment);

    /**
     * bo -> entity
     */
    @Mappings({
            @Mapping(source = "type", target = "type", qualifiedByName = "type2Code"),
            @Mapping(source = "status", target = "status", qualifiedByName = "status2Code")
    })
    Comment convert(CommentBO commentBO);

    @Named("type2Code")
    default Integer type2Code(BusinessType businessType) {
        return businessType.ordinal();
    }

    @Named("status2Code")
    default Integer status2Code(CommentStatus commentStatus) {
        return commentStatus.ordinal();
    }

    @Named("code2Status")
    default CommentStatus code2Status(Integer status) {
        return CommentStatus.getByCode(status);
    }

    @Named("code2Type")
    default BusinessType code2Type(Integer type) {
        return BusinessType.getByCode(type);
    }

}
