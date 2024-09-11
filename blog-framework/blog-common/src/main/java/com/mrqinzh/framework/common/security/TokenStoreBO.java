package com.mrqinzh.framework.common.security;

import com.mrqinzh.framework.common.domain.pojo.BO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenStoreBO implements BO {

    private LoginUser user;
    private boolean authenticated;

    public TokenStoreBO(UserDetailsImpl userDetails) {
        this.user = new LoginUser(userDetails);
    }

}
