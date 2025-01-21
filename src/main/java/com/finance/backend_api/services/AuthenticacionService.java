package com.finance.backend_api.services;

import com.finance.backend_api.repositories.UserRepository;
import com.finance.backend_api.request.AuthRequest;
import com.finance.backend_api.response.AuthResponse;
import com.finance.backend_api.util.JwtUtils;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),  // Usamos el email para autenticar
                        request.getPassword()));

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtUtils.generateToken(user);  // El JWT ahora tendrá el email como "sub"
        return AuthResponse.builder()
                .jwt(jwtToken)
                .build();
    }


    public boolean verifyToken(String token) {
        try {
            String username = jwtUtils.extractUsername(token);
            return jwtUtils.validateToken(token, username);
        } catch (JwtException ex) {
            return false;
        }
    }
}


