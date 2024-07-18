package com.finance.backend_api.repositories;

import com.finance.backend_api.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    // custom method

}
