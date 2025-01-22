package com.finance.backend_api.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequest {

    private String email;
    private String newPassword;
    private String oldPassword;

    public ChangePasswordRequest(String email, String newPassword){
        this.email = email;
        this.newPassword = newPassword;
    }

}
