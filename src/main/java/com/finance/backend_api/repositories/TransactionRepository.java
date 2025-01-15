package com.finance.backend_api.repositories;

import com.finance.backend_api.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCompanyCompanyId(Long companyId);

    List<Transaction> findByCompanyCompanyIdAndTransacionType(Long companyId, String transactionType);

    List<Transaction> findByCategoryCategoryIdAndCompanyCompanyId(Long categoryId, Long companyId);
    // custom method

}
