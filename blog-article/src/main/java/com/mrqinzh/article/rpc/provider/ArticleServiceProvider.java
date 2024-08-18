package com.mrqinzh.article.rpc.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.apis.article.ArticleService;
import com.mrqinzh.apis.comment.CommentService;
import com.mrqinzh.common.domain.entity.Article;
import com.mrqinzh.common.domain.entity.Tag;
import com.mrqinzh.common.domain.entity.User;
import com.mrqinzh.common.domain.enums.AppStatus;
import com.mrqinzh.common.exception.BizException;
import com.mrqinzh.common.utils.BizAssert;
import com.mrqinzh.common.utils.DateUtil;
import com.mrqinzh.common.utils.MyUtil;
import com.mrqinzh.common.domain.dto.PageDTO;
import com.mrqinzh.common.domain.vo.article.ArticleVo;
import com.mrqinzh.article.mapper.ArticleMapper;
import com.mrqinzh.article.mapper.TagMapper;
import com.mrqinzh.framework.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@DubboService
public class ArticleServiceProvider implements ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceProvider.class);

//    @DubboReference
    private CommentService commentService;
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private TagMapper tagMapper;

    @Override
    public Article safeGet(Long id) {
        return Optional.ofNullable(articleMapper.selectById(id)).orElseThrow(() -> new BizException("获取指定文章失败"));
    }

    @Override
    public Article get(Integer id) {
        return articleMapper.selectById(id);
    }

    @Override
    public Page<Article> list(PageDTO pageDTO) {
        Page<Article> page = new Page<>(pageDTO.getCurrentPage(), pageDTO.getPageSize());
        articleMapper.selectPage(page, null);
        return page;
    }

    @Override
    public Article getDetail(Integer articleId) {
        Article article = articleMapper.getById(articleId);
        BizAssert.notNull(articleId, "文章查询失败，查询id为空");
        return article;
    }

    @Override
    public void addView(Integer articleId) {
        Article article = get(articleId);
        if (article == null) {
            return;
        }
        article.setViews(article.getViews() + 1);
        articleMapper.updateById(article);
    }

    @Override
    @Transactional
    public void add(ArticleVo articleVo) {
        BizAssert.notNull(articleVo, "文章添加失败，文章信息为空");
        articleVo.setArticleSummary(MyUtil.stripHtml(articleVo.getArticleSummary()));

        User user = RedisUtil.get("user");

        BizAssert.notNull(user, "用户异常。！");
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);

        // 设置文章作者，优先为realName
        article.setAuthor(StringUtils.isNotBlank(user.getRealName()) ? user.getRealName() : user.getNickname());

        // 如果添加文章时，没有上传文章的封面图，系统将从选择的标签中，随机选择一个标签所对应的图片为其设置为封面。
        if (StringUtils.isBlank(article.getCoverImg())) {
            article.setCoverImg(getArticleCoverImgByTag(article.getTag()));
        }

        Date now = new Date();
        // 初始化文章的固定信息
        article.setViews(0);
        article.setUserId(user.getId());

        articleMapper.insert(article);

        logger.info("新增文章了。。。 => 时间：{}", DateUtil.date2String(now));
    }

    @Override
    @Transactional
    public void update(ArticleVo articleVo) {
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

    /**
     * 此处执行删除文章时(逻辑删除)，删除文章对应的评论
     */
    @Override
    @Transactional
    public void delete(Integer articleId) {
        articleMapper.deleteStatus(articleId);
        commentService.deleteByTypeId("articleId", articleId);
    }

    private String getArticleCoverImgByTag(String articleTag) {
        if (StringUtils.isBlank(articleTag)) return null;
        String[] tags = articleTag.split(",");
        int threshold = 0; // 定义随机阈值
        while (true) {
            int i = MyUtil.randomInt(tags.length);
            String currTag = tags[i];
            QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(Tag::getName, currTag);
            List<Tag> tagList = tagMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(tagList) && StringUtils.isNotBlank(tagList.get(0).getCoverImg())) {
                return tagList.get(0).getCoverImg();
            }
            if (threshold++ > 4) {
                throw new BizException(AppStatus.BAD_PARAMETER_REQUEST, "对不起，系统里好像没有选择标签的相关图片，请重新选择标签，或者上传自己的封面图！！！");
            }
        }
    }

}
