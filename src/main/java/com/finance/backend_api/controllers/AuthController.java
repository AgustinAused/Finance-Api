package com.finance.backend_api.controllers;

import com.finance.backend_api.request.AuthRequest;
import com.finance.backend_api.response.AuthResponse;
import com.finance.backend_api.services.AuthenticacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth API", description = "API Endpoints for Authentication")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthenticacionService authenticacionService;

    public AuthController(AuthenticacionService authenticacionService) {
        this.authenticacionService = authenticacionService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest authRequest) {
        logger.info("Authentication request received for email: {}", authRequest.getEmail());

        try {
             AuthResponse res = authenticacionService.authenticate(authRequest);
            logger.info("Authentication successful for email: {}", authRequest.getEmail());
            logger.info("JWT generated for email: {}", authRequest.getEmail());
            return ResponseEntity.ok().body(res);
        } catch (Exception ex) {
            logger.error("Authentication failed for email: {}", authRequest.getEmail(), ex);
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

}
