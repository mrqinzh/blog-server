package com.mrqinzh.user.api;

import com.mrqinzh.user.domain.constant.ApiConstant;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = ApiConstant.NAME)
public interface RoleApi {


}
