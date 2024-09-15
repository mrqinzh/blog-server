package com.mrqinzh.user.domain.bo;

import com.mrqinzh.framework.common.domain.bo.BO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserBO implements BO {

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
