package com.finance.backend_api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private String companyName;

    public UserRequest() {
    }

    public UserRequest(String username, String password, String email, String first_name, String last_name, boolean active, String companyName) {
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.companyName = companyName;
    }
}
