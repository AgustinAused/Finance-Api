package com.finance.backend_api.controllers;

import com.finance.backend_api.DTOs.CategoryIncomeExpenseDTO;
import com.finance.backend_api.DTOs.MonthlyTransactionDTO;
import com.finance.backend_api.services.GraphicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graphic")
@Tag(name = "Graphic API", description = "")
@RequiredArgsConstructor
public class GraphicController {

    private final GraphicService graphicService;


    // Endpoint para obtener total mensual por empresa
    @GetMapping("/monthly/{companyId}")
    public ResponseEntity<?> getMonthlyTransactions(@PathVariable Long companyId) {
        List<MonthlyTransactionDTO> transactions = graphicService.getMonthlyTransaction(companyId);
        return ResponseEntity.ok(transactions);
    }

    // endpoint para obtener ingresos por categoria por empresa
    @GetMapping("/income-expense-categories")
    public ResponseEntity<?> getIncomeAndExpenseCompanyByCategory(@RequestParam Long companyId) {
        List<CategoryIncomeExpenseDTO> incomeCategoryDTOList = graphicService.getIncomeAndExpenseCompanyByCategory(companyId);
        return ResponseEntity.ok(incomeCategoryDTOList);
    }

}
