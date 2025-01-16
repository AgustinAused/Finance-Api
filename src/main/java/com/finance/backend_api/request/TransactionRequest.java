package com.finance.backend_api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class TransactionRequest {

    private Long company_id;
    private Long category_id;
    private Long user_id;
    private String transaction_type;
    private double amount;
    private Date date;

//    optional
    private String receipt_url;
    private String description;
    private Long id;


    public TransactionRequest() {
    }

    public TransactionRequest(Long company_id, Long category_id, Long user_id, String transaction_type, double amount, Date date, Optional<String> receipt_url, Optional<String> description) {
        this.company_id = company_id;
        this.category_id = category_id;
        this.user_id = user_id;
        this.transaction_type = transaction_type;
        this.amount = amount;
        this.date = date;
        this.receipt_url = receipt_url.orElse(null);
        this.description = description.orElse(null);
    }


}
