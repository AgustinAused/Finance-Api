package com.finance.backend_api.models;

import com.finance.backend_api.dtos.CategoryRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;


    public boolean equals(CategoryRequest categoryRequest) {
        if (categoryRequest == null) return false;
        return this.name.equals(categoryRequest.getName()) &&
                this.description.equals(categoryRequest.getDescription()) &&
                this.company.getCompanyId().equals(categoryRequest.getCompanyId());


    }

}
