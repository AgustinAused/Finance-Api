package com.finance.backend_api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangePasswordRequest {

    private String email;
    private String newPassword;

}
