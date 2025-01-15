package com.finance.backend_api.controllers;


import com.finance.backend_api.dtos.TransactionRequest;
import com.finance.backend_api.models.Transaction;
import com.finance.backend_api.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
