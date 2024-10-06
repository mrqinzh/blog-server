package com.mrqinzh.framework.common.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class StoreToken implements Serializable {

    private LoginUser user;
    private boolean authenticated;

}
