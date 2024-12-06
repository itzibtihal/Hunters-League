package org.youcode.hunterleague.web.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.service.DTOs.SearchUserDTO;
import org.youcode.hunterleague.service.UserService;
import org.youcode.hunterleague.web.VMs.UserVMs.UpdateUserVM;
import org.youcode.hunterleague.web.VMs.UserVMs.UserVM;
import org.youcode.hunterleague.web.VMs.mapper.UpdateUserVMMapper;
import org.youcode.hunterleague.web.VMs.mapper.UserVMMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;
    private final UserVMMapper userVMMapper;
    private final UpdateUserVMMapper updateUserVMMapper;


    @GetMapping
    public ResponseEntity<Page<UserVM>> getAllUsers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Page<User> users = userService.getAllUsersPaginated(page, size);
        List<UserVM> userVMS = users.stream().map(userVMMapper::toUserVM).toList();
        Page<UserVM> userVMPage = new PageImpl<>(userVMS, users.getPageable(), users.getTotalElements());
        return ResponseEntity.ok(userVMPage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable UUID id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok("deleted successfully");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed to delete user");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserVM>> searchUsers(@RequestBody SearchUserDTO searchUserDTO) {
        List<User> result = userService.searchUsers(searchUserDTO);
        List<UserVM> userVMS = result.stream().map(userVMMapper::toUserVM).toList();
        return ResponseEntity.ok(userVMS);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<UserVM> updateUser(@PathVariable UUID userId,@RequestBody @Valid UpdateUserVM updateData) {
        User user = updateUserVMMapper.toUser(updateData);
        User updatedUser = userService.updateUser(userId,user);
        UserVM userVM = userVMMapper.toUserVM(updatedUser);
        return ResponseEntity.ok(userVM);
    }
}
