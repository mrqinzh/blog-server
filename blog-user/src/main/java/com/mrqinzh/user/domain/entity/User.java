package com.mrqinzh.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;

import java.util.Date;
import java.util.List;

public class User extends BaseEntity {

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
    @TableField(exist = false)
    private String qqNo;
    @TableField(exist = false)
    private String wxNo;
    @TableField(exist = false)
    private List<Role> roles;

    public String getName() {
        return username;
    }

    public String getPassword() {
        return pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getLoginLastTime() {
        return loginLastTime;
    }

    public void setLoginLastTime(Date loginLastTime) {
        this.loginLastTime = loginLastTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String getWxNo() {
        return wxNo;
    }

    public void setWxNo(String wxNo) {
        this.wxNo = wxNo;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
