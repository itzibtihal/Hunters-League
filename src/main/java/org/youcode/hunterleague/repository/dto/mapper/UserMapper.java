package org.youcode.hunterleague.repository.dto.mapper;


import org.mapstruct.factory.Mappers;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.repository.dto.RegisterDTO;
import org.youcode.hunterleague.utils.PasswordUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", imports = {PasswordUtil.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", expression = "java(PasswordUtil.hashPassword(registerDTO.getPassword()))")
    @Mapping(target = "joinDate", expression = "java(java.time.LocalDateTime.now())")
    User toUser(RegisterDTO registerDTO);

}
