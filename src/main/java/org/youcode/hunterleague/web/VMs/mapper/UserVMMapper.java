package org.youcode.hunterleague.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.web.VMs.UserVMs.UserVM;

@Mapper(componentModel = "spring")
public interface UserVMMapper {

    UserVM toUserVM(User user);
    User toUser(UserVM userVM);
}
