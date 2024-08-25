package com.mrqinzh.article.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.domain.entity.Article;
import com.mrqinzh.article.domain.entity.Tag;
import com.mrqinzh.article.domain.vo.ArticleVO;
import com.mrqinzh.article.mapper.ArticleMapper;
import com.mrqinzh.article.mapper.TagMapper;
import com.mrqinzh.article.rpc.CommentApiClient;
import com.mrqinzh.framework.common.domain.dto.PageDTO;
import com.mrqinzh.framework.common.domain.enums.AppStatus;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.utils.BizAssert;
import com.mrqinzh.framework.common.utils.MyUtil;
import com.mrqinzh.framework.redis.utils.RedisUtil;
import com.mrqinzh.user.domain.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private TagMapper tagMapper;
    @Resource
    private CommentApiClient commentApiClient;

    @Override
    public Article safeGet(Long id) {
        return Optional.ofNullable(articleMapper.selectById(id)).orElseThrow(() -> new BizException("获取指定文章失败"));
    }

    @Override
    public Article get(Long id) {
        return articleMapper.selectById(id);
    }

    @Override
    public Page<Article> list(PageDTO pageDTO) {
        Page<Article> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize());
        articleMapper.selectPage(page, null);
        return page;
    }

    @Override
    public Article getDetail(Long articleId) {
        Article article = articleMapper.getById(articleId);
        BizAssert.notNull(articleId, "文章查询失败，查询id为空");
        return article;
    }

    @Override
    public void addView(Long articleId) {
        Article article = get(articleId);
        if (article == null) {
            return;
        }
        article.setViews(article.getViews() + 1);
        articleMapper.updateById(article);
    }

    @Override
    public void add(ArticleVO articleVo) {
        BizAssert.notNull(articleVo, "文章添加失败，文章信息为空");
        articleVo.setArticleSummary(MyUtil.stripHtml(articleVo.getArticleSummary()));

        UserDTO user = RedisUtil.get("user");

        BizAssert.notNull(user, "用户异常。！");
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);

        // 设置文章作者，优先为realName
        article.setAuthor(StringUtils.isNotBlank(user.getRealName()) ? user.getRealName() : user.getNickname());

        // 如果添加文章时，没有上传文章的封面图，系统将从选择的标签中，随机选择一个标签所对应的图片为其设置为封面。
        if (StringUtils.isBlank(article.getCoverImg())) {
            article.setCoverImg(getArticleCoverImgByTag(article.getTag()));
        }

        // 初始化文章的固定信息
        article.setViews(0);
        article.setUserId(user.getId());

        articleMapper.insert(article);
    }

    @Override
    public void update(ArticleVO articleVo) {
        // 判断传入文章的Id是否存在
        if (articleVo == null || articleVo.getId() == null) {
            throw new BizException(AppStatus.BAD_PARAMETER_REQUEST);
        }

        Article origin = articleMapper.getById(articleVo.getId());
        if (origin == null) {
            throw new BizException(AppStatus.BAD_PARAMETER_REQUEST, "当前文章错误。");
        }

        origin.setTitle(articleVo.getArticleTitle());
        // 获取文章摘要，截取内容的前100
        origin.setSummary(MyUtil.stripHtml(articleVo.getArticleSummary()));
        origin.setCoverImg(articleVo.getArticleCoverImg());
        origin.setContentMd(articleVo.getArticleContentMd());
        origin.setTag(articleVo.getArticleTag());
        origin.setType(articleVo.getArticleType());
        // 设置更新时间
        origin.setUpdateTime(new Date());
        articleMapper.updateById(origin);
    }

    @Override
    public void delete(Long articleId) {
        articleMapper.deleteStatus(articleId);
        commentApiClient.deleteByTypeId("articleId", articleId);
    }

    private String getArticleCoverImgByTag(String articleTag) {
        if (StringUtils.isBlank(articleTag)) return null;
        String[] tags = articleTag.split(",");
        int threshold = 0; // 定义随机阈值
        while (true) {
            int i = MyUtil.randomInt(tags.length);
            String currTag = tags[i];
            List<Tag> tagList = tagMapper.selectList(new LambdaQueryWrapper<Tag>().eq(Tag::getName, currTag));
            if (!CollectionUtils.isEmpty(tagList) && StringUtils.isNotBlank(tagList.get(0).getCoverImg())) {
                return tagList.get(0).getCoverImg();
            }
            if (threshold++ > 4) {
                throw new BizException(AppStatus.BAD_PARAMETER_REQUEST, "对不起，系统里好像没有选择标签的相关图片，请重新选择标签，或者上传自己的封面图！！！");
            }
        }
    }
}
