package com.mrqinzh.framework.security.core;

import cn.hutool.core.map.MapUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class LoginUser {

    public static final String INFO_KEY_NICKNAME = "nickname";

    /**
     * 用户编号
     */
    private Long id;
    /**
     * 额外的用户信息
     */
    private Map<String, String> info;

}
