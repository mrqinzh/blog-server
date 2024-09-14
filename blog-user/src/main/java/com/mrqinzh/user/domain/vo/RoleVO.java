package com.mrqinzh.user.domain.vo;

import com.mrqinzh.framework.common.domain.pojo.vo.VO;
import lombok.Data;

@Data
public class RoleVO implements VO {

    private Long id;

    private String roleName;
    private String description;

    private String createTime;

}
