package com.mrqinzh.auth.domain.convert;

import com.mrqinzh.framework.security.core.UserDetailsImpl;
import com.mrqinzh.user.domain.user.LoginUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoginUserConvert {

    LoginUserConvert INSTANCE = Mappers.getMapper(LoginUserConvert.class);

    UserDetailsImpl convert(LoginUserResponse response);

}
