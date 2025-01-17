package com.finance.backend_api.repositories;

import com.finance.backend_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // custom method

    Optional<List<Category>> findCategoryByCompanyId(Long id);
}
