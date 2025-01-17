package com.finance.backend_api.controllers;

import com.finance.backend_api.models.Category;
import com.finance.backend_api.request.CategoryRequest;
import com.finance.backend_api.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
@Tag(name = "Category Controller", description = "API for managing categories")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


//    agregar categoría
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody CategoryRequest categoryRequest){
        Category newCategory= categoryService.addCategory(categoryRequest);
        return ResponseEntity.ok().body(Map.of("Status", "success", "data", newCategory, "path", "/api/categories"));
    }

//    eliminar categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
         Category category = categoryService.deleteCategory(id);
        return ResponseEntity.ok().body(Map.of("Status", "success", "data", category, "path", "/api/categories/"+id));
    }

//    actualizar categoría
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest){
        Category updateCategory  = categoryService.updateCategory(id,categoryRequest);
        return ResponseEntity.ok().body(Map.of("Status", "success", "data", updateCategory, "path", "/api/categories/"+id));
    }

//    obtener categoría por compania
    @GetMapping("/{companyId}")
    public ResponseEntity<?> getCategoryByCompanyId(@PathVariable Long companyId){
        List<Category> categoryList = categoryService.getCategoryByCompanyId(companyId);
        return ResponseEntity.ok().body(Map.of("Status", "success", "data", categoryList, "total", categoryList.size(), "path", "/api/categories/"+companyId));
    }




}
