package com.mrqinzh.user.domain.role;

import com.mrqinzh.framework.common.domain.enums.RoleType;
import com.mrqinzh.framework.common.domain.pojo.dto.RespDTO;
import lombok.Data;

@Data
public class RoleRespDTO implements RespDTO {

    private Long id;
    private RoleType roleName;

}
