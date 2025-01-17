package com.finance.backend_api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TransactionDTO {

    private String id;
    private String type;
    private double amount;
    private String description;
    private String categoryName;
    private String userName;
    private String createdAt;

    public TransactionDTO() {
    }




}
