package com.mrqinzh.apis.login;

import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.domain.dto.PageDTO;

public interface LoginLogService {

    Resp list(PageDTO pageDTO);

}
