package com.mrqinzh.user.domain.vo;

import com.mrqinzh.framework.common.domain.pojo.vo.VO;
import lombok.Data;

import java.util.List;

@Data
public class AppUserInfoVO implements VO {

    private Long userId;
    private String name;
    private String avatar;
    private List<String> roles;
    private List<MenuVO> menuVOS;

}
