package com.mrqinzh.auth.domain.convert;

import com.mrqinzh.framework.common.security.UserDetailsImpl;
import com.mrqinzh.user.domain.user.UserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserDetailsImpl convert(UserRespDTO dto);

}
