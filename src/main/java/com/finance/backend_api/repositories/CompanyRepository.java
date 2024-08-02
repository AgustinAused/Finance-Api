package com.finance.backend_api.repositories;

import com.finance.backend_api.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    //Custom Method

    Optional<Company> findByName(String name);
}
