package com.mrqinzh.article.domain.convert;

import com.mrqinzh.article.domain.bo.ArticleBO;
import com.mrqinzh.article.domain.dto.ArticleReqDTO;
import com.mrqinzh.article.domain.dto.ArticleRespDTO;
import com.mrqinzh.article.domain.entity.Article;
import com.mrqinzh.article.domain.vo.ArticleReqVO;
import com.mrqinzh.article.domain.vo.ArticleRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleConvert {

    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    ArticleBO convert2BO(Article article);

    ArticleRespDTO convert2RespDTO(ArticleBO articleBO);

    ArticleReqDTO convert2ReqDTO(ArticleReqVO articleReqVO);

    ArticleBO convert2BO(ArticleReqDTO articleReqDTO);

    Article convert(ArticleBO articleBO);

    ArticleRespVO convert2RespVO(ArticleRespDTO respDTO);


}
