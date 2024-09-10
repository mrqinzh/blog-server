package com.mrqinzh.auth.service;

import com.mrqinzh.auth.domain.convert.UserConvert;
import com.mrqinzh.framework.common.security.UserDetailsImpl;
import com.mrqinzh.framework.common.utils.CollectionUtils;
import com.mrqinzh.user.api.UserApi;
import com.mrqinzh.user.domain.user.UserRespDTO;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserApi userApiService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserRespDTO userDTO = userApiService.getByUsername(username);
        if (userDTO == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetailsImpl userDetails = UserConvert.INSTANCE.convert(userDTO);
        if (CollectionUtils.isNotEmpty(userDTO.getRoles())) {
            userDetails.setAuthorities(userDTO.getRoles().stream().map(r -> new SimpleGrantedAuthority(r.getRoleName().name())).collect(Collectors.toList()));
        }

        return userDetails;
    }

}
