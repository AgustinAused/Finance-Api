package com.finance.backend_api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private String type;
    private double amount;
    private String description;
    private String categoryName;
    private String userName;
    private String createdAt;
    private String receiptUrl;

    public TransactionDTO(Long id, String type, Double amount, String description, String categoryName, String userName, String receiptUrl) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.categoryName = categoryName;
        this.userName = userName;
        this.receiptUrl = receiptUrl;
    }
}
