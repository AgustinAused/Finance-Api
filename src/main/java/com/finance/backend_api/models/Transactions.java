package com.finance.backend_api.models;

import jakarta.persistence.*;

import java.util.Date;

public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transaccion_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Companies company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "amount", nullable = false, columnDefinition = "DECIMAL(10,2)")
    private double amount;

    @Column(name = "transaction_type", nullable = false)
    private String transaccion_type;

    @Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date timestamp;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories category;

    @Column(name = "description")
    private String description;

    @Column(name = "receipt_url", columnDefinition = "VARCHAR(255) CHECK (receipt_url ~* '^(http|https)://')")
    private String receipt_url;

}
