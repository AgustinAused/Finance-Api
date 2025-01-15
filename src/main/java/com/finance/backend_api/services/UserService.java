package com.finance.backend_api.services;

import com.finance.backend_api.dtos.UserRequest;
import com.finance.backend_api.exceptions.CompanyNotFoundException;
import com.finance.backend_api.exceptions.UserExistException;
import com.finance.backend_api.models.Company;
import com.finance.backend_api.models.User;
import com.finance.backend_api.repositories.CompanyRepository;
import com.finance.backend_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final CompanyRepository companyRepository;


    public UserService(UserRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public User addUser(UserRequest userRequest) {
        //verify if user already exists or other filters
        User userSaved = null;
        Optional<User> existingUser = repository.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserExistException("User already exists");
        }
        Optional<Company> companyOpt = companyRepository.findById(userRequest.getCompany_id());
        if (companyOpt.isEmpty()) {
            throw new CompanyNotFoundException("Company not found");
        }
        User user = new User();
        user.setEmail(userRequest.getEmail());
        //generate password
        user.setPassword(generatePassword());
        user.setActive(false);
        user.setFirstName(userRequest.getFirst_name());
        user.setLastName(userRequest.getLast_name());
        user.setUsername(userRequest.getUsername());
        user.setCompany(companyOpt.get());

        //save user
        userSaved = repository.save(user);
        return userSaved;
    }

    public User getUserByEmail(String email) {
        return repository.findByEmail(email).orElseThrow(() -> new UserExistException("User not found"));
    }

    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserExistException("User not found"));
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

    private String generatePassword() {
        //generate password
        return "password";
    }

}
