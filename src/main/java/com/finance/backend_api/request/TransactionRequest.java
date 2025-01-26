package com.finance.backend_api.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class TransactionRequest {

    private Long company_id;
    private Long category_id;
    private Long user_id;
    private double amount;
    private Date date;

    private String transaction_type;
//    optional
    private String receipt_url;
    private String description;
    private Long id;

    public TransactionRequest(){

    }

    public TransactionRequest(Long company_id, Long category_id, Long user_id, double amount, Date date, String transaction_type, String description ) {
        this.company_id = company_id;
        this.category_id = category_id;
        this.user_id = user_id;
        this.amount = amount;
        this.date = date;
        this.transaction_type = transaction_type;
        this.description = description;
    }


}
