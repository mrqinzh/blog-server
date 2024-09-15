package com.mrqinzh.user.rpc.provider;

import com.mrqinzh.framework.common.utils.CollectionUtils;
import com.mrqinzh.user.api.UserApi;
import com.mrqinzh.user.domain.convert.RoleConvert;
import com.mrqinzh.user.domain.convert.UserConvert;
import com.mrqinzh.user.domain.entity.Role;
import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.user.LoginUserResponse;
import com.mrqinzh.user.domain.dto.UserRespDTO;
import com.mrqinzh.user.service.RoleService;
import com.mrqinzh.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserApiProviderClient implements UserApi {

    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;

    @Override
    public LoginUserResponse getByUsername(String username) {
        UserRespDTO user = userService.getByUsername(username);
        if (user == null) {
            return null;
        }
        LoginUserResponse resp = new LoginUserResponse();
        resp.setId(user.getId());
        resp.setUsername(user.getUsername());
        resp.setNickname(user.getNickname());
        resp.setAvatar(user.getAvatar());
        resp.setPassword(user.getPwd());

        List<Role> userRoles = roleService.getRolesByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(userRoles)) {
            resp.setRoles(userRoles.stream().map(RoleConvert.INSTANCE::convert).collect(Collectors.toList()));
        }
        return resp;
    }
}
