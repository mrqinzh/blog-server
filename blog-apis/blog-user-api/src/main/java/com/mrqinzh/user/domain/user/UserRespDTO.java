package com.mrqinzh.user.domain.user;

import com.mrqinzh.framework.common.domain.pojo.DTO;
import com.mrqinzh.user.domain.role.RoleRespDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserRespDTO implements DTO {

    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private String password;

    private List<RoleRespDTO> roles;

}
