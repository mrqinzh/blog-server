package com.mrqinzh.user.domain.user;

import com.mrqinzh.framework.common.domain.rpc.RpcResponse;
import com.mrqinzh.user.domain.role.RoleRespDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LoginUserResponse implements RpcResponse {

    private Long id;
    private String username;
    private String nickname;
    private String avatar;
    private String password;

    private List<RoleRespDTO> roles;

}
