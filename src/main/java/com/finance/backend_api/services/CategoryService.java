package com.finance.backend_api.services;

import com.finance.backend_api.dtos.CategoryRequest;
import com.finance.backend_api.exceptions.CategoryEquals;
import com.finance.backend_api.exceptions.CategoryNotFoundException;
import com.finance.backend_api.exceptions.CompanyNotFoundException;
import com.finance.backend_api.models.Category;
import com.finance.backend_api.models.Company;
import com.finance.backend_api.repositories.CategoryRepository;
import com.finance.backend_api.repositories.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    private final CompanyRepository companyRepository;

    public CategoryService(CategoryRepository rep, CompanyRepository comRep){
        this.repository = rep;
        this.companyRepository = comRep;
    }

    //Method CRUD

    public void addCategory(CategoryRequest categoryRequest){
        Category category = new Category();
        Optional<Company> company = companyRepository.findById(categoryRequest.getCompanyId());
        if (company.isEmpty()){
            throw new CompanyNotFoundException("This company does not exist");
        }
        category.setCompany(company.get());
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        repository.save(category);
    }

    public void deleteCategory(Long id){
        Optional<Category> category = repository.findById(id);
        if (category.isEmpty()){
            throw new CategoryNotFoundException("This category does not exist");
        }
    }

    public void updateCategory(CategoryRequest categoryRequest) {
        Optional<Category> category = repository.findById(categoryRequest.getId());
        if (category.isEmpty()) {
            throw new CategoryNotFoundException("This category does not exist");
        }
        Optional<Company> company = companyRepository.findById(categoryRequest.getCompanyId());
        if (company.isEmpty()) {
            throw new CompanyNotFoundException("This company does not exist");
        }
        if (category.get().equals(categoryRequest)){
            throw new CategoryEquals("This category is the same");
        }
        category.get().setCompany(company.get());
        category.get().setName(categoryRequest.getName());
        category.get().setDescription(categoryRequest.getDescription());
        repository.save(category.get());
    }
}
