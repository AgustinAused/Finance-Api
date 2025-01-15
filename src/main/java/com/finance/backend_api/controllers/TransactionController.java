package com.finance.backend_api.controllers;


import com.finance.backend_api.dtos.TransactionRequest;
import com.finance.backend_api.models.Transaction;
import com.finance.backend_api.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest transactionRequest) {
        try {
            Transaction transaction = transactionService.saveTransaction(transactionRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteTransaction(@RequestParam Long transactionId) {
        try {
            Transaction transaction = transactionService.deleteTransaction(transactionId);
            return ResponseEntity.status(HttpStatus.OK).body(transaction);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


//    @PutMapping("/update")
//    public ResponseEntity<?> updateTransaction(@RequestBody TransactionRequest transactionRequest) {
//        try {
//            Transaction transaction = transactionService.updateTransaction(transactionRequest);
//            return ResponseEntity.status(HttpStatus.OK).body(transaction);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }




}
