package com.finance.backend_api.controllers;


import com.finance.backend_api.DTOs.TransactionDTO;
import com.finance.backend_api.request.TransactionRequest;
import com.finance.backend_api.models.Transaction;
import com.finance.backend_api.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.saveTransaction(transactionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("status", "success", "data", transaction)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionService.updateTransaction(id, transactionRequest);
        return ResponseEntity.ok(
                Map.of("status", "success", "data", transaction)
        );
    }



//    get transaction
    @GetMapping("/{companyId}")
    public ResponseEntity<?> getTransactionByCompanyId(@PathVariable Long companyId) {
        List<TransactionDTO> transactionList = transactionService.getTransactionsByCompanyId(companyId);
        return ResponseEntity.ok(
                Map.of("status", "success", "data", transactionList)
        );
    }

    @GetMapping("/{companyId}/expenses")
    public ResponseEntity<?> getExpenses(@PathVariable Long companyId) {
        List<Transaction> transactionList = transactionService.getExpensesByCompanyId(companyId);
        return ResponseEntity.ok(
                Map.of("status", "success", "data", transactionList)
        );
    }

    @GetMapping("/{companyId}/income")
    public ResponseEntity<?> getIncome(@PathVariable Long companyId) {
        List<Transaction> transactionList = transactionService.getIncomesByCompanyId(companyId);
        return ResponseEntity.ok(
                Map.of("status", "success", "data", transactionList)
        );
    }

    @GetMapping("/{companyId}/{categoryId}")
    public ResponseEntity<?> getTransactionByCategory(@PathVariable Long companyId, @PathVariable Long categoryId) {
        List<Transaction> transactionList = transactionService.getTransactionsCategoryIdAndCompanyId(categoryId, companyId);
        return ResponseEntity.ok(
                Map.of("status", "success", "data", transactionList)
        );
    }


}
