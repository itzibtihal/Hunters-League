package org.youcode.hunterleague.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.hunterleague.domain.User;
import org.youcode.hunterleague.exception.user.InvalidUserDataException;
import org.youcode.hunterleague.repository.dto.RegisterDTO;
import org.youcode.hunterleague.service.RegisterService;

@RestController
@RequestMapping("v1/api/register")
@Validated
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            User user = registerService.registerUser(registerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (InvalidUserDataException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
