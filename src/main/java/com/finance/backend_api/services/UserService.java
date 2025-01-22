package com.finance.backend_api.services;

import com.finance.backend_api.DTOs.UserDTO;
import com.finance.backend_api.request.ChangePasswordRequest;
import com.finance.backend_api.request.UserRequest;
import com.finance.backend_api.exceptions.UserExistException;
import com.finance.backend_api.exceptions.UserNotFoundException;
import com.finance.backend_api.models.Company;
import com.finance.backend_api.models.User;
import com.finance.backend_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final CompanyService companyService;
    private final PasswordEncoder passwordEncoder;


    public UserDTO addUser(UserRequest userRequest) {
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
        user.setAvatarUrl(userRequest.getAvatar());
        user.setCompany(companyOpt);

        //guardar usuario
        User newUser = repository.save(user);
        return new UserDTO(newUser.getId(), newUser.getEmail(), newUser.getFirstName(), newUser.getLastName(),
                newUser.isActive(), newUser.getCompany(), newUser.getAvatarUrl());
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

    public UserDTO updateUser(Long id, UserRequest user) {
        // Buscar si el usuario existe por su email
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Actualizar los campos del usuario
        existingUser.setFirstName(user.getFirst_name());
        existingUser.setLastName(user.getLast_name());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getEmail());

        // Guardar el usuario actualizado
        existingUser = repository.save(existingUser);

        // Devolver el UserDTO
        return new UserDTO(existingUser.getId(), existingUser.getEmail(), existingUser.getFirstName(),
                existingUser.getLastName(), existingUser.isActive(), existingUser.getCompany(), existingUser.getAvatarUrl());
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

    public UserDTO changePassword(ChangePasswordRequest request) {
        // Buscar al usuario
        User user = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        // Validar si el usuario está activo
        if (!user.isActive()) {
            user.setActive(true);
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        repository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setActive(user.isActive());
        userDTO.setCompany(user.getCompany());
        return userDTO;
    }


    private String generatePassword() {
        //generate password
        return passwordEncoder.encode("password");
    }

}
