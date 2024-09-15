package com.mrqinzh.article.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.article.dal.repo.ArticleRepository;
import com.mrqinzh.article.domain.bo.ArticleBO;
import com.mrqinzh.article.domain.convert.ArticleConvert;
import com.mrqinzh.article.domain.dto.ArticleReqDTO;
import com.mrqinzh.article.domain.dto.ArticleRespDTO;
import com.mrqinzh.article.domain.dto.TagRespDTO;
import com.mrqinzh.article.rpc.CommentApiClient;
import com.mrqinzh.framework.common.domain.page.PageCondition;
import com.mrqinzh.framework.common.exception.BizException;
import com.mrqinzh.framework.common.exception.ErrorCode;
import com.mrqinzh.framework.common.utils.BizAssert;
import com.mrqinzh.framework.common.utils.MyUtil;
import com.mrqinzh.framework.mybatis.utils.PageUtils;
import com.mrqinzh.framework.redis.utils.RedisUtil;
import com.mrqinzh.user.domain.user.UserDTO;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private TagService tagService;
    @Resource
    private ArticleTagService articleTagService;
    @Resource
    private CommentApiClient commentApiClient;
    @Resource
    private ArticleRepository articleRepository;

    @Override
    public ArticleRespDTO safeGet(Long id) {
        return Optional.ofNullable(get(id)).orElseThrow(() -> new BizException("获取指定文章失败"));
    }

    @Override
    public ArticleRespDTO get(Long id) {
        ArticleBO articleBO = articleRepository.queryById(id);
        return ArticleConvert.INSTANCE.convert2RespDTO(articleBO);
    }

    @Override
    public Page<ArticleRespDTO> Page(PageCondition pageReq) {
        Page<ArticleBO> page = articleRepository.page(pageReq);

        return PageUtils.convert(page, ArticleConvert.INSTANCE::convert2RespDTO);
    }

    @Override
    public ArticleRespDTO getDetail(Long articleId) {
        ArticleBO articleBO = articleRepository.queryById(articleId);
        BizAssert.notNull(articleBO, "文章查询失败，查询id为空");
        return ArticleConvert.INSTANCE.convert2RespDTO(articleBO);
    }

    @Override
    public void addView(Long articleId) {
        ArticleBO articleBO = articleRepository.queryById(articleId);
        if (articleBO == null) {
            return;
        }
        articleBO.setViews(articleBO.getViews() + 1);
        articleRepository.updateById(articleBO);
    }

    @Override
    public void add(ArticleReqDTO articleReqDTO) {
        BizAssert.notNull(articleReqDTO, "文章添加失败，文章信息为空");

        UserDTO user = RedisUtil.get("user");

        BizAssert.notNull(user, "用户异常。！");
        ArticleBO articleBO = ArticleConvert.INSTANCE.convert2BO(articleReqDTO);

        // 设置文章作者，优先为realName
        articleBO.setAuthor(StringUtils.isNotBlank(user.getRealName()) ? user.getRealName() : user.getNickname());

        // 如果添加文章时，没有上传文章的封面图，系统将从选择的标签中，随机选择一个标签所对应的图片为其设置为封面。
        if (StringUtils.isBlank(articleBO.getCoverImg())) {
            articleBO.setCoverImg(computeArticleCoverImgByTag(articleBO.getTag()));
        }

        // 初始化文章的固定信息
        articleBO.setViews(0);
        articleBO.setUserId(user.getId());

        articleRepository.insert(articleBO);
    }

    @Override
    public void update(ArticleReqDTO articleReqDTO) {
        // 判断传入文章的Id是否存在
        if (articleReqDTO == null || articleReqDTO.getId() == null) {
            throw new BizException(ErrorCode.BAD_PARAMETER);
        }

        ArticleBO origin = articleRepository.queryById(articleReqDTO.getId());
        if (origin == null) {
            throw new BizException(ErrorCode.BAD_PARAMETER, "当前文章错误。");
        }

        origin.setTitle(articleReqDTO.getArticleTitle());
        // 获取文章摘要，截取内容的前100
        origin.setSummary(MyUtil.stripHtml(articleReqDTO.getArticleSummary()));
        origin.setCoverImg(articleReqDTO.getArticleCoverImg());
        origin.setContentMd(articleReqDTO.getArticleContentMd());
        origin.setTag(articleReqDTO.getArticleTag());
        origin.setType(articleReqDTO.getArticleType());
        // 设置更新时间
        origin.setUpdateTime(new Date());

        articleRepository.updateById(origin);
    }

    @Override
    public void delete(Long articleId) {
        articleRepository.delete(articleId);
        commentApiClient.deleteByTypeId("articleId", articleId);
    }

    private String computeArticleCoverImgByTag(String articleTag) {
        if (StringUtils.isBlank(articleTag)) {
            return null;
        }
        String[] tags = articleTag.split(",");
        int threshold = 0; // 定义随机阈值
        while (true) {
            int i = MyUtil.randomInt(tags.length);
            String currTag = tags[i];
            List<TagRespDTO> tagList = tagService.queryByName(currTag);
            if (!CollectionUtils.isEmpty(tagList) && StringUtils.isNotBlank(tagList.get(0).getCoverImg())) {
                return tagList.get(0).getCoverImg();
            }
            if (threshold++ > 4) {
                throw new BizException(ErrorCode.BAD_PARAMETER, "对不起，系统里好像没有选择标签的相关图片，请重新选择标签，或者上传自己的封面图！！！");
            }
        }
    }

    @Override
    public void upgradeArticleTag() {
        List<ArticleBO> allArticles = articleRepository.queryAll();

        for (ArticleBO articleBO : allArticles) {
            String tags = articleBO.getTag();
            if (StringUtils.isNotBlank(tags)) {
                String[] tagArray = tags.split(",");
                for (String tag : tagArray) {
                    List<TagRespDTO> tagRespDTOS = tagService.queryByName(tag);
                    tagRespDTOS.forEach(t -> articleTagService.add(articleBO.getId(), t.getId()));
                }
            }
        }

    }
}
