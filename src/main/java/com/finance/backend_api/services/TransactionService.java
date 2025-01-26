package com.finance.backend_api.services;

import com.finance.backend_api.DTOs.CashFlowDTO;
import com.finance.backend_api.DTOs.TransactionDTO;
import com.finance.backend_api.request.TransactionRequest;
import com.finance.backend_api.exceptions.TransactionNotFoundException;
import com.finance.backend_api.models.Category;
import com.finance.backend_api.models.Company;
import com.finance.backend_api.models.Transaction;
import com.finance.backend_api.models.User;
import com.finance.backend_api.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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


    public Page<TransactionDTO> getTransactionsByCompanyId(Long companyId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactions = transactionRepository.findByCompanyId(companyId, pageable);

        // Definir un formato personalizado
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        // Convertir cada entidad Transaction a un DTO
        return transactions.map(transaction -> new TransactionDTO(
                transaction.getId(),
                transaction.getType(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getCategory().getName(),
                transaction.getUser().getFirstName() + " " + transaction.getUser().getLastName(),
                formatter.format(transaction.getTimestamp()),
                transaction.getReceiptUrl()
        ));
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
