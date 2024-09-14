package com.mrqinzh.comment.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrqinzh.framework.common.resp.PageResp;
import com.mrqinzh.framework.common.web.controller.BaseController;

import java.util.function.Function;

public abstract class BaseCommentController extends BaseController {

    public <T> PageResp<T> pageResp(Page<T> page) {
        return PageResp.ok(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    public <T, R> PageResp<R> pageResp(Page<T> page, Function<T, R> mapping) {
        return PageResp.ok(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords(), mapping);
    }

}
