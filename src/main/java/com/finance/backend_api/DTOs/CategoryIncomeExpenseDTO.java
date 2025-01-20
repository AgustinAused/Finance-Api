package com.finance.backend_api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class CategoryIncomeExpenseDTO {

    private String category;
    private double incomeAmount;
    private double expenseAmount;
}
