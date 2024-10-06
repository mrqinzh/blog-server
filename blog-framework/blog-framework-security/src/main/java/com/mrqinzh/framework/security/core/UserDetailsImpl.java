package com.mrqinzh.framework.security.core;

import com.mrqinzh.framework.common.security.LoginUser;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String nickname;
    private String username;
    private String password;
    private String avatar;
    private List<SimpleGrantedAuthority> authorities = Collections.emptyList();

    public UserDetailsImpl(LoginUser loginUser) {
        this.id = loginUser.getId();
        this.nickname = loginUser.getNickname();
        this.username = loginUser.getUsername();
        this.avatar = loginUser.getAvatar();
        this.authorities = loginUser.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public LoginUser toLoginUser() {
        LoginUser user = new LoginUser();
        user.setId(id);
        user.setNickname(nickname);
        user.setUsername(username);
        user.setAvatar(avatar);
        user.setRoles(authorities.stream().map(SimpleGrantedAuthority::getAuthority).toList());
        return user;
    }

    @Override
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
