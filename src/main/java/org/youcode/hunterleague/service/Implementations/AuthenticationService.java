package org.youcode.hunterleague.service.Implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.youcode.hunterleague.domain.entities.User;
import org.youcode.hunterleague.domain.enums.Role;
import org.youcode.hunterleague.repository.UserRepository;
import org.youcode.hunterleague.security.JwtService;
import org.youcode.hunterleague.service.AuthService;
import org.youcode.hunterleague.web.VMs.AuthVMs.AuthenticationRequest;
import org.youcode.hunterleague.web.VMs.AuthVMs.AuthenticationResponse;
import org.youcode.hunterleague.web.VMs.AuthVMs.RegisterRequest;


@Service
@RequiredArgsConstructor
public class AuthenticationService implements AuthService {

    private final UserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        var user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .cin(request.getCin())
                .nationality(request.getNationality())
                .role(Role.valueOf(request.getRole()))
                .build();

        appUserRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = appUserRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
