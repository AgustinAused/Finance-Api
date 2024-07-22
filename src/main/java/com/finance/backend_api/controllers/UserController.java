package com.finance.backend_api.controllers;

import com.finance.backend_api.exceptions.UserExistException;
import com.finance.backend_api.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service){
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Object> addUser(@RequestBody UserRequest){
        try{
            String response = service.addUser();
        } catch (UserExistException e){

        }
    }
}
