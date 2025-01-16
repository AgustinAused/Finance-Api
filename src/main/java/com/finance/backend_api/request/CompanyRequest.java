package com.finance.backend_api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompanyRequest {

    private Long id;

    private String name;

    private String phone;

    private String address;

    public CompanyRequest(String name, String phone, String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }


}
