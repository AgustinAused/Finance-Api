package com.finance.backend_api.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserRequest {

    private String username;
    private String email;
    private String first_name;
    private String last_name;
    private String companyName;
    private MultipartFile avatar;

    public UserRequest() {
    }

    public UserRequest(String username, String email, String first_name, String last_name, String companyName) {
        this.username = username;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.companyName = companyName;
    }
    public UserRequest(String email, String first_name, String last_name, MultipartFile avatar) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }
}
