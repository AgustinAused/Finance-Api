package com.finance.backend_api.services;


import com.finance.backend_api.DTOs.CashFlowDTO;
import com.finance.backend_api.models.Transaction;
import com.finance.backend_api.repositories.TransactionRepository;
import com.finance.backend_api.util.PeriodConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CashFlowService {


    private final TransactionRepository repository;


//    getCashFlowForPeriod
    public CashFlowDTO getCashFlowForPeriod(String period) {

        // convertir periodo a start y end date
        Date[] dateRange = PeriodConvertor.convertPeriodToDateRange(period);
        Date startDate = dateRange[0];
        Date endDate = dateRange[1];


        List<Transaction> transactions = repository.findByTimestampBetween(startDate, endDate);

        // Calcular ingresos, gastos y flujo de caja neto
        double income = 0;
        double expenses = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getType().equals("income")) {
                income += transaction.getAmount();
            } else if (transaction.getType().equals("expense")) {
                expenses += transaction.getAmount();
            }
        }

        double netCashFlow = income - expenses;

        // Retornar el DTO con la información del flujo de caja
        return new CashFlowDTO(period, income, expenses, netCashFlow);
    }
}

