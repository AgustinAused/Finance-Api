package com.finance.backend_api.repositories;

import com.finance.backend_api.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
