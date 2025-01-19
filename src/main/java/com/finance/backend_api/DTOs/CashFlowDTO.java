package com.finance.backend_api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
public class CashFlowDTO {

    private String period;
    private double income;
    private double expenses;
    private double netCashFlow;
}
