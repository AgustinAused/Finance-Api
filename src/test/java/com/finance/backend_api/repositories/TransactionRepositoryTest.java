package com.finance.backend_api.repositories;

import com.finance.backend_api.DTOs.TransactionDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void testFindByCompanyId() {
        Pageable pageable = PageRequest.of(0, 10);
        Long companyId = 1L; // Asegúrate de usar un ID válido de tu base de datos
//        Page<TransactionDTO> transactions = transactionRepository.findByCompanyId(companyId, pageable);

//        assertNotNull(transactions);
//        assertFalse(transactions.isEmpty());
    }
}
