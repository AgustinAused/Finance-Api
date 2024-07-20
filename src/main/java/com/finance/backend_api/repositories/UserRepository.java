package com.finance.backend_api.repositories;

import com.finance.backend_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // custom method
    @Query(value = "SELECT u FROM Users u WHERE u.email = :email;",
            nativeQuery = true)
    Optional<User> findByEmail(String email);


}
