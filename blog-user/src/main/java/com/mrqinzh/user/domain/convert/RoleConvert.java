package com.mrqinzh.user.domain.convert;

import com.mrqinzh.user.domain.entity.Role;
import com.mrqinzh.user.domain.role.RoleRespDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleRespDTO convert(Role role);

}
