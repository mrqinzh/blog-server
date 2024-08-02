package com.mrqinzh.common.vo.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

    private Integer id;

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
