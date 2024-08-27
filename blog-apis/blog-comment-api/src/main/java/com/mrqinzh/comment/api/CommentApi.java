package com.mrqinzh.comment.api;

import com.mrqinzh.comment.domain.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstant.NAME)
public interface CommentApi {

    @GetMapping(ApiConstant.PREFIX + "/deleteByTypeId")
    void deleteByTypeId(@RequestParam("articleOrCommentId") String articleOrCommentId, @RequestParam("id") Long id);
}
