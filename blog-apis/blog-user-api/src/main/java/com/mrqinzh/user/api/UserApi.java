package com.mrqinzh.user.api;

import com.mrqinzh.framework.common.domain.rpc.ServiceResponse;
import com.mrqinzh.user.domain.constant.ApiConstant;
import com.mrqinzh.user.domain.user.LoginUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = ApiConstant.NAME)
public interface UserApi {

    @PostMapping(ApiConstant.PREFIX + "/getByUsername")
    ServiceResponse<LoginUserResponse> getByUsername(@RequestParam("username") String username);

}
