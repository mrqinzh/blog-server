package com.mrqinzh.common.entity;

import com.mrqinzh.commons.entity.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class LoginLog extends BaseEntity {

    private Integer userId;
    private String token;
    private String ip;

    private Date loginTime;

}
