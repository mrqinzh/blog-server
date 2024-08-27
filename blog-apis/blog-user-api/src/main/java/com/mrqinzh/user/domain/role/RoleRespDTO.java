package com.mrqinzh.user.domain.role;

import com.mrqinzh.framework.common.domain.dto.DTO;
import com.mrqinzh.framework.common.domain.enums.RoleType;
import lombok.Data;

@Data
public class RoleRespDTO implements DTO {

    private Long id;
    private RoleType roleName;

}
