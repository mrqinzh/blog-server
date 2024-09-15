package com.mrqinzh.user.domain.dto;

import com.mrqinzh.framework.common.domain.dto.RespDTO;
import lombok.Data;

import java.util.Date;

@Data
public class UserRespDTO implements RespDTO {

    private Long id;
    private String mobile;
    /**
     * 用户最后登录时间
     */
    private Date loginLastTime;
    private String avatar;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * email
     */
    private String email;
    private String realName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String pwd;
    private Date createTime;
    private Date updateTime;

}
