package com.finance.backend_api.services;

import com.finance.backend_api.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository){
        this.repository = repository;
    }

    //add company


}
