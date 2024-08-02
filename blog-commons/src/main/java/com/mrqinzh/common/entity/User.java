package com.mrqinzh.common.entity;

import com.mrqinzh.commons.auth.SecurityUser;
import com.mrqinzh.commons.entity.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class User extends BaseEntity implements SecurityUser {

    private String telephone;
    /**
     * 用户最后登录时间
     */
    private Date loginLastTime;
    private String userAvatar;
    /**
     * 昵称
     */
    private String userNickname;
    /**
     * email
     */
    private String userEmail;
    private String userRealName;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPwd;
    private String qqNo;
    private String wxNo;

    private List<Role> roles;

    @Override
    public String getName() {
        return userName;
    }

    @Override
    public String getPassword() {
        return userPwd;
    }
}
