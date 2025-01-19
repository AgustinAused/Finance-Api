package com.finance.backend_api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class MonthlyTransactionDTO {

    private int month;
    private int year;
    private double totalIncome;
    private double totalExpenses;
}
