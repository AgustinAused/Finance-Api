package com.finance.backend_api.controllers;

import com.finance.backend_api.dtos.UserRequest;
import com.finance.backend_api.exceptions.CompanyNotFoundException;
import com.finance.backend_api.exceptions.UserExistException;
import com.finance.backend_api.models.User;
import com.finance.backend_api.services.EventService;
import com.finance.backend_api.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final EventService eventService;

    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> addUser(@RequestBody UserRequest userRequest) {
        try {
            logger.info("Adding new user: {}", userRequest.getEmail());

            User userResponse = userService.addUser(userRequest);

            Map<String, String> details = new HashMap<>();
            details.put("email", userRequest.getEmail());
            details.put("username", userRequest.getUsername());

            eventService.addEvent(userResponse.getId(), "User created", details);

            logger.info("User created successfully: {}", userResponse.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status", "success", "data", userResponse));
        } catch (UserExistException e) {
            logger.warn("User already exists: {}", userRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (CompanyNotFoundException e) {
            logger.warn("Company not found for user: {}", userRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while creating user: {}", userRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }
}
