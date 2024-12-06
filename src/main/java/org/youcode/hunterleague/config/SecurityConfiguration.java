package org.youcode.hunterleague.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.youcode.hunterleague.security.JwtAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.youcode.hunterleague.domain.enums.Permission.*;
import static org.youcode.hunterleague.domain.enums.Role.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**")
                    .permitAll()
//                .requestMatchers("/api/v1/species/**").hasRole(ADMIN.name())
/*

                .requestMatchers(GET, "/api/v1/competition/**").hasAuthority(CAN_VIEW_COMPETITIONS.name())
                .requestMatchers(POST, "/api/v1/competition/**").hasAuthority(CAN_MANAGE_COMPETITIONS.name())
                .requestMatchers(PUT, "/api/v1/competition/**").hasAuthority(CAN_MANAGE_COMPETITIONS.name())
                .requestMatchers(DELETE, "/api/v1/competition/**").hasAuthority(CAN_MANAGE_COMPETITIONS.name())
*/

                .requestMatchers("/api/v1/hunt").hasAnyRole(ADMIN.name(), JURY.name())

                .requestMatchers(POST,"/api/v1/participation/**").hasAuthority(CAN_PARTICIPATE.name())
                .requestMatchers(GET,"/api/v1/participation/**").hasAuthority(CAN_VIEW_RANKINGS.name())

                .requestMatchers("/api/v1/users/**").hasRole(ADMIN.name())
                .anyRequest()
                    .authenticated()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
