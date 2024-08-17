package com.mrqinzh.article.rpc.provider;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mrqinzh.apis.tag.TagService;
import com.mrqinzh.common.entity.Tag;
import com.mrqinzh.common.enums.AppStatus;
import com.mrqinzh.common.exception.BizException;
import com.mrqinzh.common.resp.PageResp;
import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;
import com.mrqinzh.article.mapper.TagMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

@DubboService
public class TagServiceProvider implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Override
    public Resp page(PageVO pageVO) {
        List<Tag> tags = tagMapper.page(pageVO);

        return PageResp.ok(tags);
    }

    @Override
    public List<Tag> getByLimit() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.last("limit 20");
        return tagMapper.selectList(queryWrapper);
    }

    @Override
    public void add(Tag tag) {
        if (StringUtils.isBlank(tag.getTagImg())) {
            throw new BizException(AppStatus.BAD_PARAMETER_REQUEST, "必须上传标签对应图片！");
        }
        tagMapper.insert(tag);
    }

    @Override
    public Resp delete(Integer id) {
        tagMapper.deleteById(id);
        return Resp.sendMsg(AppStatus.DELETE_SUCCESS);
    }

    @Override
    public void update(Tag tag) {
        if (tag.getId() == null || StringUtils.isBlank(tag.getTagImg())) {
            throw new BizException(AppStatus.BAD_PARAMETER_REQUEST);
        }
        tagMapper.updateById(tag);
    }

    @Override
    public Tag getById(Integer id) {
        return tagMapper.selectById(id);
    }

}
