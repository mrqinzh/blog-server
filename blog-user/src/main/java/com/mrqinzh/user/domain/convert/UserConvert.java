package com.mrqinzh.user.domain.convert;

import com.mrqinzh.user.domain.bo.UserBO;
import com.mrqinzh.user.domain.entity.User;
import com.mrqinzh.user.domain.dto.UserRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    User convert(UserBO userBO);

    UserRespDTO convert2RespDTO(User user);
    UserRespDTO convert2RespDTO(UserBO userBO);

    UserBO convert2BO(User user);

    UserRespDTO convert2DTO(UserBO userBO);

}
