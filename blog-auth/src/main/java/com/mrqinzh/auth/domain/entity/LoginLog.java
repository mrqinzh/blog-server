package com.mrqinzh.auth.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("t_login_log")
public class LoginLog extends BaseEntity {

    private Long userId;
    private String token;
    private String ip;

}
