package com.finance.backend_api.services;

import com.finance.backend_api.exceptions.UserExistException;
import com.finance.backend_api.models.User;
import com.finance.backend_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final EventService eventService;

    public UserService(UserRepository repository, EventService service) {
        this.repository = repository;
        this.eventService = service;
    }

    public String addUser(User user) {
        //verify if user already exists or other filters
        repository.findByEmail(user.getEmail()).ifPresentOrElse( u -> {
            throw new UserExistException("User already exists");
        }, () -> {
            User user1 = repository.save(user);
            eventService.addEvent(user1.getUser_id(), "User created", null);
        });
        return "User saved successfully";
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UserExistException("User not found"));
    }

    public String deleteUserByEmail(String email) {
        repository.deleteByEmail(email);
        return "User deleted successfully";
    }

    public String updateUser(User user) {
        repository.findByEmail(user.getEmail()).ifPresentOrElse( u -> {
            repository.save(user);
        }, () -> {
            throw new UserExistException("User not found");
        });
        return "User updated successfully";
    }

}
