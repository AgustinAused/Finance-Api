package com.finance.backend_api.services;

import com.finance.backend_api.dtos.UserRequest;
import com.finance.backend_api.exceptions.CompanyNotFoundException;
import com.finance.backend_api.exceptions.UserExistException;
import com.finance.backend_api.exceptions.UserNotFoundException;
import com.finance.backend_api.models.Company;
import com.finance.backend_api.models.User;
import com.finance.backend_api.repositories.CompanyRepository;
import com.finance.backend_api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final CompanyService companyService;


    public UserService(UserRepository repository, CompanyService companyService) {
        this.repository = repository;
        this.companyService = companyService;
    }

    public User addUser(UserRequest userRequest) {
        // Verificar si el usuario ya existe
        Optional<User> existingUser = repository.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            throw new UserExistException("User already exists");
        }
        // Verificar si la empresa existe, si no la crea
        Company companyOpt = companyService.findOrCreateCompany(userRequest.getCompanyName());
        User user = new User();
        user.setEmail(userRequest.getEmail());
        //genera password
        user.setPassword(generatePassword());
        user.setActive(false);
        user.setFirstName(userRequest.getFirst_name());
        user.setLastName(userRequest.getLast_name());
        user.setUsername(userRequest.getUsername());
        user.setCompany(companyOpt);

        //guardar usuario
        return repository.save(user);
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
        repository.findByEmail(user.getEmail()).ifPresentOrElse( u -> repository.save(user), () -> {
            throw new UserNotFoundException("User not found");
        });
        return "User updated successfully";
    }


    public User activateUser(String email) {
        User user = getUserByEmail(email);
        user.setActive(true);
        return repository.save(user);
    }


    public User deactivateUser(String email) {
        User user = getUserByEmail(email);
        user.setActive(false);
        return repository.save(user);
    }

    public User assignUserToCompany(String email, String companyName) {
        User user = getUserByEmail(email);
        Company company = companyService.findOrCreateCompany(companyName);
        user.setCompany(company);
        return repository.save(user);
    }
    public User assignUserToCompany(String email, long companyId) {
        User user = getUserByEmail(email);
        Company company = companyService.getCompany(companyId);
        user.setCompany(company);
        return repository.save(user);
    }


    private String generatePassword() {
        //generate password
        return "password";
    }

}
