package com.mrqinzh.article.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@TableName("ARTICLE_TAG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTagRelation extends BaseEntity {

    private Long articleId;
    private Long tagId;
    private String tagName;

}
