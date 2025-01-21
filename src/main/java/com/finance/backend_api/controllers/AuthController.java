package com.finance.backend_api.controllers;

import com.finance.backend_api.request.AuthRequest;
import com.finance.backend_api.response.AuthResponse;
import com.finance.backend_api.services.AuthenticacionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
            return ResponseEntity.ok().body(Map.of("data", res, "status", "success"));
        } catch (Exception ex) {
            logger.error("Authentication failed for email: {}", authRequest.getEmail(), ex);
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }


    @GetMapping("/verifytoken")
    public ResponseEntity<?> verifyToken(@RequestParam String token) {
        logger.info("Verifying token: {}", token);
        try {
            boolean res = authenticacionService.verifyToken(token);
            if (res) {
                logger.info("Token verified successfully: {}", token);
                return ResponseEntity.ok().body(Map.of("status", "success", "message", "Token is valid"));
            } else {
                logger.warn("Token verification failed: {}", token);
                return ResponseEntity.status(401).body(Map.of("status", "error", "message", "Token is invalid"));
            }
        } catch (Exception ex) {
            logger.error("Token verification failed: {}", token, ex);
            return ResponseEntity.status(401).body(Map.of("status", "error", "message", "Token is invalid"));
        }
    }
}

