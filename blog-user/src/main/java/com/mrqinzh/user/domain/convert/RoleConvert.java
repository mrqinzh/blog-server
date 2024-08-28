package com.mrqinzh.user.domain.convert;

import com.mrqinzh.user.domain.entity.Role;
import com.mrqinzh.user.domain.role.RoleRespDTO;
import com.mrqinzh.user.domain.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleConvert {

    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleRespDTO convert(Role role);

    @Mappings(value = {
            @Mapping(target = "description", source = "roleName.desc")
    })
    RoleVO convert2VO(Role role);

}
