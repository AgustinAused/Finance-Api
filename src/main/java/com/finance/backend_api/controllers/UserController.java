package com.finance.backend_api.controllers;

import com.finance.backend_api.dtos.UserRequest;
import com.finance.backend_api.exceptions.CompanyNotFoundException;
import com.finance.backend_api.exceptions.UserExistException;
import com.finance.backend_api.models.User;
import com.finance.backend_api.services.EventService;
import com.finance.backend_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController("/user")
public class UserController {

    private final UserService service;
    private final EventService eventService;

    public UserController(UserService service,EventService eventService){
        this.service = service;
        this.eventService = eventService;

    }

    @PostMapping("/")
    public ResponseEntity<Object> addUser(@RequestBody UserRequest userRequest){
        try{
            User userResponse = service.addUser(userRequest);
            Map<String, String> details = new HashMap<>();
            details.put("email", userRequest.getEmail());
            details.put("username", userRequest.getUsername());
            eventService.addEvent(userResponse.getUser_id(), "User created",details);
        } catch (UserExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (CompanyNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
