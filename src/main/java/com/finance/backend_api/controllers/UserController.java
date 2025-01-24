package com.finance.backend_api.controllers;

import com.finance.backend_api.DTOs.UserDTO;
import com.finance.backend_api.request.ChangePasswordRequest;
import com.finance.backend_api.request.UserRequest;
import com.finance.backend_api.exceptions.CompanyNotFoundException;
import com.finance.backend_api.exceptions.UserExistException;
import com.finance.backend_api.models.User;
import com.finance.backend_api.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "API for managing users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) {
        try {
            logger.info("Adding new user: {}", userRequest.getEmail());

            UserDTO userResponse = userService.addUser(userRequest);

            Map<String, String> details = new HashMap<>();
            details.put("email", userRequest.getEmail());
            details.put("username", userRequest.getUsername());

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

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        try {
            logger.info("Getting user by email: {}", email);

            User userResponse = userService.getUserByEmail(email);

            logger.info("User found: {}", userResponse.getId());
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", "success", "data", userResponse));
        } catch (UserExistException e) {
            logger.warn("User not found: {}", email, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while getting user: {}", email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteUserByEmail(@PathVariable String email) {
        try {
            logger.info("Deleting user by email: {}", email);

            String response = userService.deleteUserByEmail(email);

            logger.info("User deleted successfully: {}", email);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", "success", "data", response));
        } catch (UserExistException e) {
            logger.warn("User not found: {}", email, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while deleting user: {}", email, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest user) {
        try {
            logger.info("Updating user: {}", user.getEmail());

            UserDTO userDTO = userService.updateUser(id,user);

            logger.info("User updated successfully: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", "success", "data", userDTO));
        } catch (UserExistException e) {
            logger.warn("User not found: {}", user.getEmail(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error while updating user: {}", user.getEmail(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        try {
            // Obtener el objeto principal del SecurityContext
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            String email;
            if (principal instanceof org.springframework.security.core.userdetails.User) {
                // Obtener el email si el principal es un objeto UserDetails
                email = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            } else if (principal instanceof String) {
                // Manejar casos donde el principal es directamente un String (aunque esto es poco común en la autenticación basada en email)
                email = (String) principal;
            } else {
                // Manejar cualquier otro caso (por defecto)
                throw new IllegalStateException("Tipo de principal no soportado: " + principal.getClass().getName());
            }

            // Obtener el usuario desde la base de datos usando el email
            User userResponse = userService.getUserByEmail(email);
            UserDTO userDTO = new UserDTO(userResponse.getId(), userResponse.getEmail(), userResponse.getFirstName(), userResponse.getLastName(), userResponse.isActive(), userResponse.getCompany(), userResponse.getAvatarUrl(), userResponse.getPhone());


            // Devolver la respuesta con el perfil
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", "success", "data", userDTO));
        } catch (Exception e) {
            logger.error("Error al obtener el perfil del usuario", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }


    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request) {
        UserDTO user = userService.changePassword(request);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("statuss", "success", "data",user));
    }


}
