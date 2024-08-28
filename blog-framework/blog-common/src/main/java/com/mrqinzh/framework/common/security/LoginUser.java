package com.mrqinzh.framework.common.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser {

    private Long userId;
    private String username;
    private String nickname;
    private String avatar;

    private List<String> roles = Collections.emptyList();

    public LoginUser(UserDetailsImpl userDetails) {
        this.userId = userDetails.getId();
        this.nickname = userDetails.getNickname();
        this.username = userDetails.getUsername();
        this.avatar = userDetails.getAvatar();
        this.roles = userDetails.getAuthorities().stream().map(SimpleGrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    public UserDetailsImpl toUserDetails() {
        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setId(userId);
        userDetails.setUsername(username);
        userDetails.setNickname(nickname);
        userDetails.setAvatar(avatar);
        userDetails.setAuthorities(roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        return userDetails;
    }

}
