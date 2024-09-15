package com.mrqinzh.article.domain.convert;

import com.mrqinzh.article.domain.bo.TagBO;
import com.mrqinzh.article.domain.dto.TagReqDTO;
import com.mrqinzh.article.domain.dto.TagRespDTO;
import com.mrqinzh.article.domain.entity.Tag;
import com.mrqinzh.article.domain.vo.TagReqVO;
import com.mrqinzh.article.domain.vo.TagRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TagConvert {

    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    Tag convert(TagBO tagBO);

    TagBO convert2BO(Tag tag);

    TagRespDTO convert2RespDTO(TagBO tagBO);

    TagRespVO convert2RespVO(TagRespDTO tagRespDTO);

    TagBO convert2BO(TagReqDTO tagReqDTO);

    TagReqDTO convert2ReqDTO(TagReqVO tagReqVO);

}
