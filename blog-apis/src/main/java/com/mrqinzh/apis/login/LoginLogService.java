package com.mrqinzh.apis.login;

import com.mrqinzh.common.resp.Resp;
import com.mrqinzh.common.vo.PageVO;

public interface LoginLogService {

    Resp list(PageVO pageDTO);

}
