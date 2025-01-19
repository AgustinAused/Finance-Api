package com.finance.backend_api.services;

import com.finance.backend_api.DTOs.MonthlyTransactionDTO;
import com.finance.backend_api.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GraphicService {

    private final TransactionRepository repository;


    public List<MonthlyTransactionDTO> getMonthlyTransaction(Long companyId){
        List<MonthlyTransactionDTO> transactions = repository.findMonthlyTransactionsByCompany(companyId);

        return transactions;
    }


}
