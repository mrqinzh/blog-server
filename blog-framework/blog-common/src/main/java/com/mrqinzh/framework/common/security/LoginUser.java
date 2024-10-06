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

    private Long id;
    private String username;
    private String nickname;
    private String avatar;

    private List<String> roles = Collections.emptyList();

}
