package com.mrqinzh.user.domain.role;

import com.mrqinzh.framework.common.domain.dto.RespDTO;
import lombok.Data;

@Data
public class RoleRespDTO implements RespDTO {

    private Long id;
    private String roleName;

}
