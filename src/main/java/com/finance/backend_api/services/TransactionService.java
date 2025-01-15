package com.finance.backend_api.services;

import com.finance.backend_api.dtos.TransactionRequest;
import com.finance.backend_api.exceptions.TransactionNotFoundException;
import com.finance.backend_api.models.Category;
import com.finance.backend_api.models.Company;
import com.finance.backend_api.models.Transaction;
import com.finance.backend_api.models.User;
import com.finance.backend_api.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CompanyService companyService;
    private final UserService userService;
    private final CategoryService categoryService;

    public TransactionService(TransactionRepository transactionRepository, CompanyService companyService, UserService userService, CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.companyService = companyService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public Transaction saveTransaction(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setType(transactionRequest.getTransaction_type());
        transaction.setTimestamp(transactionRequest.getDate());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setReceiptUrl(transactionRequest.getReceipt_url());

        Company company = companyService.getCompany(transactionRequest.getCompany_id());
        transaction.setCompany(company);
        User user = userService.getUserById(transactionRequest.getUser_id());
        transaction.setUser(user);
        Category category = categoryService.getCategory(transactionRequest.getCategory_id());
        transaction.setCategory(category);
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }


    public List<Transaction> getTransactionsByCompanyId(Long companyId) {
        return transactionRepository.findByCompanyId(companyId);
    }

    public List<Transaction> getTransactionsByCategoryId(Long categoryId, Long companyId) {
        return transactionRepository.findByCategoryIdAndCompanyId(categoryId, companyId);
    }

    public List<Transaction> getTransactionsByCompanyIdAndUserId(Long companyId) {
        return transactionRepository.findByCompanyId(companyId);
    }

    public List<Transaction> getExpensesByCompanyId(Long companyId) {
        return transactionRepository.findByCompanyIdAndType(companyId, "expense");
    }

    public List<Transaction> getIncomesByCompanyId(Long companyId) {
        return transactionRepository.findByCompanyIdAndType(companyId, "income");
    }

    public Transaction deleteTransaction(Long transactionId) throws Exception {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isPresent()) {
            transactionRepository.delete(transaction.get());
            return transaction.get();
        } else {
            throw new TransactionNotFoundException("Transaction not found");
        }
    }





}
