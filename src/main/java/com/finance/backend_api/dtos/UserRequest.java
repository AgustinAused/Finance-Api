package com.finance.backend_api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private boolean active;
    private Long company_id;

    public UserRequest() {
    }

    public UserRequest(String username, String password, String email, String first_name, String last_name, boolean active, Long company_id) {
    }
}
