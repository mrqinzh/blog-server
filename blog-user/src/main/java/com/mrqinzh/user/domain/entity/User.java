package com.mrqinzh.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.mrqinzh.framework.mybatis.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
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

}
