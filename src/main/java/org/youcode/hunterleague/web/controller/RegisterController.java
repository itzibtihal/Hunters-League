package org.youcode.hunterleague.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.service.RegisterService;
import org.youcode.hunterleague.web.vm.RegisterVM;
import org.youcode.hunterleague.web.vm.mapper.UserMapper;

@RestController
@RequestMapping("v1/api/register")
@Validated
public class RegisterController {

    private final RegisterService registerService;
    private final UserMapper userMapper;

    @Autowired
    public RegisterController(RegisterService registerService,UserMapper userMapper) {
        this.registerService = registerService;
        this.userMapper = userMapper;
    }


//    @PostMapping
//    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegisterVM registerVM) {
//
//            User user = userMapper.toEntity(registerVM);
//            User registeredUser = registerService.registerUser(user);
//            UserDTO userDTO = userMapper.toDto(registeredUser);
//            return new ResponseEntity<>(userDTO,HttpStatus.OK);
//
//    }


    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterVM registerVM) {
        User user = userMapper.toEntity(registerVM);
        registerService.registerUser(user);
        return ResponseEntity.ok("User registered successfully");
    }


}