package com.mrqinzh.framework.common.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser {

    private Long userId;
    private String username;
    private String nickname;
    private String avatar;

    private List<String> roles;

    public LoginUser(UserDetailsImpl userDetails) {
        this.userId = userDetails.getId();
        this.nickname = userDetails.getNickname();
        this.username = userDetails.getUsername();
        this.avatar = userDetails.getAvatar();
        this.roles = userDetails.getAuthorities().stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList());
    }

}
