package com.finance.backend_api.controllers;

import com.finance.backend_api.DTOs.CashFlowDTO;
import com.finance.backend_api.services.CashFlowService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/financial")
@Tag(name = "Financial API",description = "API for managing financial")
@RequiredArgsConstructor
@SecurityRequirement(name = "BearerAuth")
public class FinancialController {

    private final CashFlowService cashFlowService;

    @GetMapping("/cashflow")
    public ResponseEntity<?> getCashFlowForPeriod(@RequestParam String period) {
        String periodLower = period.toLowerCase();
        CashFlowDTO cashFlow = cashFlowService.getCashFlowForPeriod(periodLower);

        return ResponseEntity.ok().body(Map.of("Status", "success", "data", cashFlow));
    }



}
