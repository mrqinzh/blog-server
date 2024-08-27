package com.mrqinzh.user.domain.user;

import com.mrqinzh.framework.common.domain.dto.BaseDTO;
import lombok.Data;

@Data
public class UserRespDTO implements BaseDTO {

    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private String password;

}
