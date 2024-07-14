package com.finance.backend_api.models;

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
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String category_id;

    @Column(name = "company_id")
    @ManyToOne(targetEntity = Companies.class, fetch = FetchType.LAZY)
    private Companies company;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
