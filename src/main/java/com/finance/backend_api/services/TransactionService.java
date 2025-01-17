package com.finance.backend_api.services;

import com.finance.backend_api.DTOs.TransactionDTO;
import com.finance.backend_api.request.TransactionRequest;
import com.finance.backend_api.exceptions.TransactionNotFoundException;
import com.finance.backend_api.models.Category;
import com.finance.backend_api.models.Company;
import com.finance.backend_api.models.Transaction;
import com.finance.backend_api.models.User;
import com.finance.backend_api.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<TransactionDTO> getTransactionsByCompanyId(Long companyId) {
        List<Transaction> transactions = transactionRepository.findByCompanyId(companyId);
        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("No transactions found");
        }
        List<TransactionDTO> transLst =  new ArrayList<>();
        for (Transaction transaction : transactions) {
            TransactionDTO transactionDTO = new TransactionDTO();
            transactionDTO.setId(transaction.getId().toString());
            transactionDTO.setAmount(transaction.getAmount());
            transactionDTO.setCreatedAt(transaction.getTimestamp().toString());
            transactionDTO.setDescription(transaction.getDescription());
            transactionDTO.setCategoryName(transaction.getCategory().getName());
            transactionDTO.setUserName(transaction.getUser().getUsername());
            transactionDTO.setType(transaction.getType());
            transLst.add(transactionDTO);
        }
        return transLst;
    }

    public List<Transaction> getTransactionsCategoryIdAndCompanyId(Long categoryId, Long companyId) {
        return transactionRepository.findByCategoryIdAndCompanyId(categoryId, companyId);
    }

    public List<Transaction> getExpensesByCompanyId(Long companyId) {
        return transactionRepository.findByCompanyIdAndType(companyId, "expense");
    }

    public List<Transaction> getIncomesByCompanyId(Long companyId) {
        return transactionRepository.findByCompanyIdAndType(companyId, "income");
    }

    public void deleteTransaction(Long transactionId){
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if (transaction.isEmpty()) {
            throw new TransactionNotFoundException("Transaction not found");
        }
        transactionRepository.deleteById(transactionId);
    }


    public Transaction updateTransaction(Long transactionId,TransactionRequest transactionReq) {
        Optional<Transaction> transactionOpt = transactionRepository.findById(transactionId);

        if (transactionOpt.isPresent()) {
            Transaction transaction = transactionOpt.get();
            transaction.setAmount(transactionReq.getAmount());
            transaction.setType(transactionReq.getTransaction_type());
            transaction.setTimestamp(transactionReq.getDate());
            transaction.setDescription(transactionReq.getDescription());
            transaction.setReceiptUrl(transactionReq.getReceipt_url());
            Company company = companyService.getCompany(transactionReq.getCompany_id());
            transaction.setCompany(company);
            User user = userService.getUserById(transaction
            .getUser().getId());
            transaction.setUser(user);
            Category category = categoryService.getCategory(transactionReq.getCategory_id());
            transaction.setCategory(category);
            return transactionRepository.save(transaction);
        } else {
            throw new TransactionNotFoundException("Transaction not exist");
        }
    }





}
