package com.mrqinzh.framework.common.security;

import com.mrqinzh.framework.common.domain.bo.BO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenStoreBO implements BO {

    private LoginUser user;

    public TokenStoreBO(UserDetailsImpl userDetails) {
        this.user = new LoginUser(userDetails);
    }

}
