package com.finance.backend_api.controllers;

import com.finance.backend_api.models.Company;
import com.finance.backend_api.request.CompanyRequest;
import com.finance.backend_api.services.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
@Tag(name = "Company Controller", description = "API for managing companies")
public class CompanyController {

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    private final CompanyService companyService;

    /**
     * Create a new company.
     */
    @PostMapping
    public ResponseEntity<?> createCompany(@RequestBody CompanyRequest companyRequest) {
        logger.info("Creating a new company: {}", companyRequest.getName());

        Company companyDTO = companyService.addCompany(companyRequest);

        logger.info("Company created successfully: {}", companyDTO.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("status", "success", "data", companyDTO));
    }

    /**
     * Update an existing company.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody CompanyRequest companyRequest) {
        logger.info("Updating company with ID: {}", id);

        Company companyDTO = companyService.updateCompany(id, companyRequest);

        logger.info("Company updated successfully: {}", companyDTO.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("status", "success", "data", companyDTO));
    }

    /**
     * Get details of a company.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Long id) {
        logger.info("Fetching details for company with ID: {}", id);

        Company companyDTO = companyService.getCompany(id);

        logger.info("Company details retrieved successfully for ID: {}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("status", "success", "data", companyDTO));
    }

    /**
     * Delete a company.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        logger.info("Deleting company with ID: {}", id);

        companyService.deleteCompany(id);

        logger.info("Company deleted successfully with ID: {}", id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Map.of("status", "success", "message", "Company deleted successfully"));
    }

}
