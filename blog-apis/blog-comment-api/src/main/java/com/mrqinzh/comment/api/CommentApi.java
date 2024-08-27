package com.mrqinzh.comment.api;

import com.mrqinzh.comment.domain.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = ApiConstant.NAME)
public interface CommentApi {

    @GetMapping(ApiConstant.PREFIX + "/deleteByTypeId")
    void deleteByTypeId(String articleOrCommentId, Long id);
}
