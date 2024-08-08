package com.mrqinzh.article.rpc.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.mrqinzh.apis.article.ArticleService;
import com.mrqinzh.apis.comment.CommentService;
import com.mrqinzh.common.entity.Article;
import com.mrqinzh.common.entity.Tag;
import com.mrqinzh.common.entity.User;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.exception.BizException;
import com.mrqinzh.common.resp.PageResp;
import com.mrqinzh.common.utils.BizAssert;
import com.mrqinzh.common.utils.DateUtil;
import com.mrqinzh.common.utils.MyUtil;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.common.vo.article.ArticleVo;
import com.mrqinzh.framework.mybatis.page.PageDTO;
import com.mrqinzh.framework.utils.RedisUtil;
import com.mrqinzh.article.mapper.ArticleMapper;
import com.mrqinzh.article.tag.TagMapper;
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
    @Resource
    private RedisUtil redisUtil;

    @Override
    public Article safeGet(Long id) {
        return Optional.ofNullable(articleMapper.selectById(id)).orElseThrow(() -> new BizException("获取指定文章失败"));
    }

    @Override
    public Article get(Integer id) {
        return articleMapper.selectById(id);
    }

    @Override
    public PageResp<Article> list(PageVO pageVO) {
        PageHelper.startPage(pageVO.getCurrentPage(), pageVO.getPageSize());
        long count = articleMapper.pageCount(pageVO);
        if (count > 0) {
            List<Article> articles = articleMapper.list(pageVO);
            return null;
        }

        return null;
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
        article.setArticleViews(article.getArticleViews() + 1);
        articleMapper.updateById(article);
    }

    @Override
    @Transactional
    public void add(ArticleVo articleVo) {
        BizAssert.notNull(articleVo, "文章添加失败，文章信息为空");
        articleVo.setArticleSummary(MyUtil.stripHtml(articleVo.getArticleSummary()));

        User user = (User) redisUtil.get("user");

        BizAssert.notNull(user, "用户异常。！");
        Article article = new Article();
        BeanUtils.copyProperties(articleVo, article);

        // 设置文章作者，优先为realName
        article.setArticleAuthor(StringUtils.isNotBlank(user.getUserRealName()) ? user.getUserRealName() : user.getUserNickname());

        // 如果添加文章时，没有上传文章的封面图，系统将从选择的标签中，随机选择一个标签所对应的图片为其设置为封面。
        if (StringUtils.isBlank(article.getArticleCoverImg())) {
            article.setArticleCoverImg(getArticleCoverImgByTag(article.getArticleTag()));
        }

        Date now = new Date();
        // 初始化文章的固定信息
        article.setArticleViews(0);
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

        origin.setArticleTitle(articleVo.getArticleTitle());
        // 获取文章摘要，截取内容的前100
        origin.setArticleSummary(MyUtil.stripHtml(articleVo.getArticleSummary()));
        origin.setArticleCoverImg(articleVo.getArticleCoverImg());
        origin.setArticleContentMd(articleVo.getArticleContentMd());
        origin.setArticleTag(articleVo.getArticleTag());
        origin.setArticleType(articleVo.getArticleType());
        // 设置更新时间
        origin.setArticleUpdateTime(new Date());
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
            queryWrapper.lambda().eq(Tag::getTagName, currTag);
            List<Tag> tagList = tagMapper.selectList(queryWrapper);
            if (!CollectionUtils.isEmpty(tagList) && StringUtils.isNotBlank(tagList.get(0).getTagImg())) {
                return tagList.get(0).getTagImg();
            }
            if (threshold++ > 4) {
                throw new BizException(AppStatus.BAD_PARAMETER_REQUEST, "对不起，系统里好像没有选择标签的相关图片，请重新选择标签，或者上传自己的封面图！！！");
            }
        }
    }

}
