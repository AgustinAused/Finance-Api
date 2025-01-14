package com.finance.backend_api.services;

import com.finance.backend_api.models.Transaction;
import com.finance.backend_api.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    public List<Transaction> getTransactionsByCompanyId(Long companyId) {
        return transactionRepository.findByCompanyId(companyId);
    }

    public List<Transaction> getTransactionsByCategoryId(Long categoryId, Long companyId) {
        return transactionRepository.findByCategoryIdAndCompany(categoryId, companyId);
    }

    public List<Transaction> getTransactionsByCompanyIdAndUserId(Long companyId) {
        return transactionRepository.findByCompanyId(companyId);
    }

    public List<Transaction> getExpensesByCompanyId(Long companyId) {
        return transactionRepository.findByCompanyIdAndTransactionType(companyId, "expense");
    }

    public List<Transaction> getIncomesByCompanyId(Long companyId) {
        return transactionRepository.findByCompanyIdAndTransactionType(companyId, "income");
    }




}
