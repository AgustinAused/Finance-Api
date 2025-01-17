package com.finance.backend_api.services;

import com.finance.backend_api.repositories.UserRepository;
import com.finance.backend_api.request.AuthRequest;
import com.finance.backend_api.response.AuthResponse;
import com.finance.backend_api.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticacionService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthenticacionService(UserRepository repository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;

    }

//    public Users register(RegisterRequest request) {
//        Users user = Users.builder().name(request.getFirstname()).last_name(request.getLastname()).email(request.getEmail()).userPassword(this.passwordEncoder.encode(request.getUserPassword())).role(request.getRole()).username(request.getUsername()).weekly_hours(request.getWeeklyHours()).skillLevel(request.getSkillLevel()).build();
//        repository.save(user);
//        return user;
//    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUtils.generateToken(user);
        return AuthResponse.builder()
                .jwt(jwtToken)
                .build();
    }
}

