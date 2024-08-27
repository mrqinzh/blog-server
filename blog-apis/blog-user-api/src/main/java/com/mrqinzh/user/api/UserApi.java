package com.mrqinzh.user.api;

import com.mrqinzh.user.domain.constant.ApiConstant;
import com.mrqinzh.user.domain.user.UserRespDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstant.NAME)
public interface UserApi {

    @PostMapping(ApiConstant.PREFIX + "/getByUsername")
    UserRespDTO getByUsername(@RequestParam("username") String username);

}
