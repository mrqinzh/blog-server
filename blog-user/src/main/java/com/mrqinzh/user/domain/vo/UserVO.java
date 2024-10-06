package com.mrqinzh.user.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserVO {

    private Long id;

    private String roleName;

    private String telephone;

    /**
     * 用户最后登录时间
     */
    private Date loginLastTime;

    private String userAvatar;

    private String userNickname;

    private String userEmail;

    private String userRealName;

    private String userName;

    private String userPwd;

    /**
     * 新密码，用于更新密码
     */
    private String newPass;

}
