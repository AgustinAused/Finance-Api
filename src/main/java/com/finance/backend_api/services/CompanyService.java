package com.finance.backend_api.services;

import com.finance.backend_api.request.CompanyRequest;
import com.finance.backend_api.exceptions.CompanyExistException;
import com.finance.backend_api.exceptions.CompanyNotFoundException;
import com.finance.backend_api.models.Company;
import com.finance.backend_api.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository repository;

    public CompanyService(CompanyRepository repository){
        this.repository = repository;
    }

    //Method CRUD

    //add company
    public Company addCompany(CompanyRequest companyRequest){

        Optional<Company> companyExist = repository.findByName(companyRequest.getName());
        if (companyExist.isPresent()){
            throw new CompanyExistException("");
        }

        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setPhone(companyRequest.getPhone());
        company.setAddress(companyRequest.getAddress());
        company.setEmail(companyRequest.getEmail());

        return repository.save(company);
    }

    public Company updateCompany(Long id, CompanyRequest companyRequest){
        Optional<Company> company = repository.findById(id);
        if(company.isEmpty()){
            throw new CompanyNotFoundException("This company does not exist");
        }
        Company company1 = company.get();
        company1.setName(companyRequest.getName());
        company1.setPhone(companyRequest.getPhone());
        company1.setAddress(companyRequest.getAddress());
        company1.setEmail(companyRequest.getEmail());
        return repository.save(company1);
    }

    public void deleteCompany(Long id){
        Optional<Company> company = repository.findById(id);
        if(company.isEmpty()){
            throw new CompanyNotFoundException("This company does not exist");
        }
        repository.delete(company.get());
    }

    // Method to the company

    public Company getCompany(Long id){
        Optional<Company> company = repository.findById(id);
        if(company.isEmpty()){
            throw new CompanyNotFoundException("This company does not exist");
        }
        return company.get();
    }

    public Company getCompanyByName(String name){
        Optional<Company> company = repository.findByName(name);
        if(company.isEmpty()){
            throw new CompanyNotFoundException("This company does not exist");
        }
        return company.get();
    }


    public Company findOrCreateCompany(String companyName) {
        Optional<Company> company = repository.findByName(companyName);
        if (company.isPresent()) {
            return company.get();
        }
        Company newCompany = new Company();
        newCompany.setName(companyName);
        return repository.save(newCompany);
    }
}
