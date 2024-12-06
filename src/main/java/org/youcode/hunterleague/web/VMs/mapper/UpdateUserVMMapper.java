package org.youcode.hunterleague.web.VMs.mapper;

import org.mapstruct.Mapper;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.web.VMs.UserVMs.UpdateUserVM;

@Mapper(componentModel = "spring")
public interface UpdateUserVMMapper {
    User toUser(UpdateUserVM updateUserVM);
    UpdateUserVM toUpdateUserVM(User user);
}
