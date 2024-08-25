package com.mrqinzh.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

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
    private String qqNo;
    private String wxNo;

}
