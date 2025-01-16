package com.finance.backend_api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CategoryRequest {

    private Long id;

    private Long companyId;
    private String name;
    private String description;

    public CategoryRequest(Long id,String name, String description){
        this.companyId = id;
        this.name = name;
        this.description = description;
    }
}
