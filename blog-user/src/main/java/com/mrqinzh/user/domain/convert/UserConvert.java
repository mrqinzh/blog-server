package com.mrqinzh.user.domain.convert;

import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.user.UserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    UserRespDTO convert(User user);

}
