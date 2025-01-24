package com.finance.backend_api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompanyRequest {

    private String name;

    private String phone;

    private String address;

    private String email;

}
