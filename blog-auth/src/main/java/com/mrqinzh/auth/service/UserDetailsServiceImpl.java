package com.mrqinzh.auth.service;

import com.mrqinzh.auth.domain.UserDetailsImpl;
import com.mrqinzh.user.api.UserApi;
import com.mrqinzh.user.domain.user.UserRespDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserApi userApiService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsImpl details = new UserDetailsImpl();
        UserRespDTO userDTO = userApiService.getByUsername(username);

        details.setUsername(userDTO.getUsername());
        details.setPassword(userDTO.getPassword());

        return details;
    }

}
