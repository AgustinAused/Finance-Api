package com.finance.backend_api.repositories;

import com.finance.backend_api.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {
    // custom method
}
