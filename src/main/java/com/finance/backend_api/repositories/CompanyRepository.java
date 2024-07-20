package com.finance.backend_api.repositories;

import com.finance.backend_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Category, Long> {
    //Custom Method
}
