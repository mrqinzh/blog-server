package com.mrqinzh.auth.domain.bo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginLogBO {

    private Long id;
    private Long userId;
    private String ip;

    private Date createTime;
}
