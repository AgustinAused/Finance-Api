package com.finance.backend_api.services;

import com.finance.backend_api.repositories.UserRepository;
import jakarta.transaction.UserTransaction;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
}
