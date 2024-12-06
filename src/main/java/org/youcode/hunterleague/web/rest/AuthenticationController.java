package org.youcode.hunterleague.web.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.hunterleague.service.AuthService;
import org.youcode.hunterleague.web.VMs.AuthVMs.AuthenticationRequest;
import org.youcode.hunterleague.web.VMs.AuthVMs.AuthenticationResponse;
import org.youcode.hunterleague.web.VMs.AuthVMs.RegisterRequest;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AuthService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody @Valid AuthenticationRequest  request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
