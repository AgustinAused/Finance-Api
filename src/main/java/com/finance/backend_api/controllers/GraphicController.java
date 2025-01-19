package com.finance.backend_api.controllers;

import com.finance.backend_api.DTOs.MonthlyTransactionDTO;
import com.finance.backend_api.services.GraphicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/graphic")
@Tag(name = "Graphic API", description = "")
@RequiredArgsConstructor
public class GraphicController {

    private final GraphicService graphicService;


    // Endpoint para obtener transacciones mensuales por empresa
    @GetMapping("/monthly/{companyId}")
    public ResponseEntity<?> getMonthlyTransactions(@PathVariable Long companyId) {
        List<MonthlyTransactionDTO> transactions = graphicService.getMonthlyTransaction(companyId);
        return ResponseEntity.ok(transactions);
    }

}
