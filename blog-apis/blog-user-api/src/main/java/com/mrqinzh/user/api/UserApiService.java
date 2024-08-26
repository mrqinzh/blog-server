package com.mrqinzh.user.api;

import com.mrqinzh.user.domain.user.UserRespDTO;

public interface UserApiService {

    UserRespDTO getByUsername(String username);

}
