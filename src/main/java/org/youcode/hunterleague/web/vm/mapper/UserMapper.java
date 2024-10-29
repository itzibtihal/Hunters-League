package org.youcode.hunterleague.web.vm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.utils.PasswordUtil;
import org.youcode.hunterleague.web.vm.RegisterVM;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {PasswordUtil.class, LocalDateTime.class})
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "password", expression = "java(PasswordUtil.hashPassword(registerVM.getPassword()))")
//    @Mapping(target = "joinDate", expression = "java(LocalDateTime.now())")
//    @Mapping(target = "firstName", source = "firstName")
//    @Mapping(target = "lastName", source = "lastName")
//    @Mapping(target = "cin", source = "cin")
//    @Mapping(target = "email", source = "email")
//    @Mapping(target = "nationality", source = "nationality")
//    @Mapping(target = "licenseExpirationDate", source = "licenseExpirationDate")
//    @Mapping(target = "role", source = "role")
    User toEntity(RegisterVM registerVM);

    RegisterVM toDto(User user);
}
