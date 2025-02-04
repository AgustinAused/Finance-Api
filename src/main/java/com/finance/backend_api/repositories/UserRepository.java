package com.finance.backend_api.repositories;

import com.finance.backend_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // custom method

    Optional<User> findByEmail(String email);

    Optional<User> deleteByEmail(String email);


}
