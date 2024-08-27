package com.mrqinzh.framework.common.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUser {

    private Long userId;
    private String username;
    private String nickname;
    private String avatar;

    public LoginUser(UserDetailsImpl userDetails) {
        this.userId = userDetails.getId();
        this.nickname = userDetails.getNickname();
        this.username = userDetails.getUsername();
        this.avatar = userDetails.getAvatar();
    }

}
